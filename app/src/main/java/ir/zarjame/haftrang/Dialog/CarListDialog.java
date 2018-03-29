package ir.zarjame.haftrang.Dialog;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Activity.CarPricesActivity;
import ir.zarjame.haftrang.Adapters.CarAdapter;
import ir.zarjame.haftrang.Adapters.CarCompanyAdapter;
import ir.zarjame.haftrang.AlarmManager.BusAlarmReceiver;
import ir.zarjame.haftrang.Models.Requests.Request_SearchBuses;
import ir.zarjame.haftrang.Models.Responses.Response_Bus;
import ir.zarjame.haftrang.Models.Responses.Response_BusCity;
import ir.zarjame.haftrang.Models.Responses.Response_Car;
import ir.zarjame.haftrang.Models.Responses.Response_CarList;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry_Data;
import ir.zarjame.haftrang.Models.Responses.Response_SearchBuses;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

import static ir.zarjame.haftrang.Tools.PublicTools.getFormattedNumber;
import static ir.zarjame.haftrang.Tools.PublicVariables.AlarmInterval;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusAmount;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusAmount;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusArrivalCode;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusArrivalName;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusDepartureCode;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusDepartureDate;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusDepartureName;
import static ir.zarjame.haftrang.Tools.SharedPref.setBusSourceDestination;

public class CarListDialog extends Dialog {

    private Unbinder unbinder;
    Context context;

    @BindView(R.id.iv_dialog_currency_alarm_icon)
    ImageView icon;

    @BindView(R.id.iv_dialog_currency_alarm_closeDialog)
    ImageView closeDialog;

    @BindView(R.id.rv_activity_car_prices_companyList)
    RecyclerView
            rv_carLists;

    @BindView(R.id.tv_dialog_carlist_price_companyTitle)
    TextView tv_title;

    String companyTitle = "";
    List<Response_Car> carList = new ArrayList<>();


    public CarListDialog(@NonNull Context context, List<Response_Car> carlist, String companytitle) {
        super(context);
        carList = carlist;
        companyTitle = companytitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_carlist_price);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(lp);

        this.setCancelable(true);
        unbinder = ButterKnife.bind(this);

        YoYo.with(Techniques.SlideInLeft)
                .duration(2000)
                .playOn(icon);


        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        CarAdapter carCompanyAdapter = new CarAdapter(carList, context);

        rv_carLists.setLayoutManager(mLayoutManager);
        rv_carLists.setItemAnimator(new DefaultItemAnimator());
        rv_carLists.setAdapter(carCompanyAdapter);

        rv_carLists.setItemViewCacheSize(200);
        rv_carLists.setDrawingCacheEnabled(true);
        rv_carLists.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        tv_title.setText(companyTitle);

        closeDialog.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               dismiss();
                                           }
                                       }

        );


    }


}


