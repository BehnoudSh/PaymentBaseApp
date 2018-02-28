package ir.haftrang.haftrang.Dialog;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.haftrang.haftrang.Activity.SplashActivity;
import ir.haftrang.haftrang.AlarmManager.BusAlarmReceiver;
import ir.haftrang.haftrang.Models.Requests.Request_SearchBuses;
import ir.haftrang.haftrang.Models.Responses.Response_Bus;
import ir.haftrang.haftrang.Models.Responses.Response_BusCity;
import ir.haftrang.haftrang.Models.Responses.Response_Inquiry_Data;
import ir.haftrang.haftrang.Models.Responses.Response_SearchBuses;
import ir.haftrang.haftrang.NetworkServices.ApiCallbacks;
import ir.haftrang.haftrang.NetworkServices.ApiHandler;
import ir.haftrang.haftrang.R;
import ir.haftrang.haftrang.Tools.PublicTools;

import static ir.haftrang.haftrang.Tools.PublicVariables.AlarmInterval;
import static ir.haftrang.haftrang.Tools.SharedPref.getBusAmount;
import static ir.haftrang.haftrang.Tools.SharedPref.getBusSourceDestination;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusAmount;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusArrivalCode;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusArrivalName;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusDepartureCode;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusDepartureDate;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusDepartureName;
import static ir.haftrang.haftrang.Tools.SharedPref.setBusSourceDestination;

public class BusAlarmDialog extends Dialog {

    private Unbinder unbinder;
    List<Response_Inquiry_Data> currencyList;
    Context context;

    @BindView(R.id.startAlarm)
    Button bt_startAlarm;

    @BindView(R.id.et_dialog_currency_alarm_amount)
    EditText alarm_amount;

    @BindView(R.id.iv_dialog_currency_alarm_icon)
    ImageView icon;

    @BindView(R.id.iv_dialog_currency_alarm_closeDialog)
    ImageView closeDialog;

    @BindView(R.id.tv_dialog_flight_alarm_sourceDestination)
    TextView tv_sourceDestination;

    @BindView(R.id.tv_dialog_flight_alarm_dateTime)
    TextView tv_dateTime;

    String sourceDestination = "";
    String date_Time = "";

    Response_BusCity selectedSource;
    Response_BusCity selectedDestination;


    String datetime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_bus_alarm);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(lp);

        this.setCancelable(true);
        unbinder = ButterKnife.bind(this);

        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .playOn(icon);


        tv_sourceDestination.setText(this.sourceDestination);
        tv_dateTime.setText(this.date_Time);

        bt_startAlarm.setOnClickListener(new View.OnClickListener() {

                                             @Override
                                             public void onClick(View v) {
                                                 if (alarm_amount.getText().toString().equals("")) {
                                                     Toast.makeText(context, "قیمت را وارد نمایید", Toast.LENGTH_SHORT).show();
                                                     YoYo.with(Techniques.Shake)
                                                             .duration(700)
                                                             .playOn(alarm_amount);
                                                     return;
                                                 }

                                                 Intent alarm = new Intent(context, BusAlarmReceiver.class);
                                                 boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
                                                 if (alarmRunning == false) {
                                                     PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarm, 0);
                                                     AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                                                     alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), AlarmInterval, pendingIntent);
                                                 }

                                                 setBusDepartureCode(selectedSource.getCode());
                                                 setBusArrivalCode(selectedDestination.getCode());
                                                 setBusArrivalName(selectedDestination.getName());
                                                 setBusDepartureName(selectedSource.getName());
                                                 setBusDepartureDate(datetime);
                                                 setBusSourceDestination(sourceDestination);
                                                 setBusAmount(Long.valueOf(alarm_amount.getText().toString().trim()));


                                                 dismiss();

                                                 Toast.makeText(context, "خبر بلیط اتوبوس از ما", Toast.LENGTH_SHORT).show();

                                                 final ProgressDialog dialog = PublicTools.ProgressDialogInstance(context, "در حال بررسی سرویس درخواستی شما");
                                                 dialog.show();

                                                 Request_SearchBuses request = new Request_SearchBuses(selectedSource.getCode(), selectedDestination.getCode(), selectedSource.getName(), selectedDestination.getName(), datetime);
                                                 ApiHandler.searchBuses(context, request, new ApiCallbacks.searchBusesInterface() {
                                                             @Override
                                                             public void onSearchBusesFailed(String message) {

                                                                 dialog.dismiss();

                                                                 AlertDialog _dialog = new AlertDialog.Builder(context)
                                                                         .setMessage("در حال حاضر مشکلی در ارتباط با سرور پیش آمد، اما ما پیوسته در حال بررسی بلیط\u200Cها خواهیم بود و شما را مطلع خواهیم ساخت")
                                                                         .setCancelable(false)
                                                                         .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                                                                             @Override
                                                                             public void onClick(DialogInterface dialog, int which) {

                                                                                 dialog.dismiss();
                                                                             }
                                                                         })
                                                                         .create();
                                                                 _dialog.show();

                                                             }

                                                             @Override
                                                             public void onSearchBusesSucceeded(Response_SearchBuses response) {
                                                                 dialog.dismiss();
                                                                 int counter = 0;

                                                                 for (Response_Bus bus : response.getItems()
                                                                         ) {
                                                                     if (bus.getPrice() < getBusAmount())
                                                                         counter++;
                                                                 }

                                                                 if (counter != 0) {

                                                                     AlertDialog _dialog = new AlertDialog.Builder(context)
                                                                             .setMessage(counter + " سرویس اتوبوس با قیمت کمتر از " + PublicTools.getThousandSeperated(getBusAmount()) + " ریال یافت شد ")
                                                                             .setCancelable(false)
                                                                             .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                                                                                 @Override
                                                                                 public void onClick(DialogInterface dialog, int which) {

                                                                                     dialog.dismiss();
                                                                                 }
                                                                             })
                                                                             .create();
                                                                     _dialog.show();


                                                                 } else {

                                                                     AlertDialog _dialog = new AlertDialog.Builder(context)
                                                                             .setMessage("در حال حاضر بلیطی منطبق با خواسته\u200Cی شما یافت نشد اما ما پیوسته در حال بررسی بلیط\u200Cها خواهیم بود و شما را مطلع خواهیم ساخت")
                                                                             .setCancelable(false)
                                                                             .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                                                                                 @Override
                                                                                 public void onClick(DialogInterface dialog, int which) {

                                                                                     dialog.dismiss();
                                                                                 }
                                                                             })
                                                                             .create();
                                                                     _dialog.show();

                                                                 }
                                                             }
                                                         }
                                                 );
                                             }
                                         }
        );

        closeDialog.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               dismiss();
                                           }
                                       }

        );
    }

    public BusAlarmDialog(@NonNull Context context, Context context1, String source_destination, String date_time, Response_BusCity selectedsource, Response_BusCity selecteddestination, String datetime) {
        super(context);
        this.context = context1;
        this.sourceDestination = source_destination;
        this.date_Time = date_time;
        this.selectedSource = selectedsource;
        this.selectedDestination = selecteddestination;
        this.datetime = datetime;
    }


}


