package ir.chichand.chichand.Dialog;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
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
import ir.chichand.chichand.AlarmManager.CurrencyAlarmReceiver;
import ir.chichand.chichand.AlarmManager.FlightAlarmReceiver;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;

import static ir.chichand.chichand.Tools.PublicVariables.AlarmInterval;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyAmount;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyName;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyType;
import static ir.chichand.chichand.Tools.SharedPref.setFlightAmount;
import static ir.chichand.chichand.Tools.SharedPref.setFlightArriavl;
import static ir.chichand.chichand.Tools.SharedPref.setFlightDeparture;
import static ir.chichand.chichand.Tools.SharedPref.setFlightDepartureDate;

public class FlightAlarmDialog extends Dialog {

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

    String sourceiata = "";

    String destinationiata = "";
    String datetime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_flight_alarm);
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

                Intent alarm = new Intent(context, FlightAlarmReceiver.class);
                boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
                if (alarmRunning == false) {
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarm, 0);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), AlarmInterval, pendingIntent);
                }


                setFlightDeparture(sourceiata);
                setFlightArriavl(destinationiata);
                setFlightDepartureDate(datetime);
                setFlightAmount(Long.valueOf(alarm_amount.getText().toString().trim()));


                dismiss();

            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public FlightAlarmDialog(@NonNull Context context, Context context1, String source_destination, String date_time, String sourceiata, String destinationiata, String datetime) {
        super(context);
        this.context = context1;
        this.sourceDestination = source_destination;
        this.date_Time = date_time;
        this.sourceiata = sourceiata;
        this.destinationiata = destinationiata;
        this.datetime = datetime;
    }


}


