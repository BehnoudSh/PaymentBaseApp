package ir.chichand.chichand.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static ir.chichand.chichand.Tools.SharedPref.getBusAmount;
import static ir.chichand.chichand.Tools.SharedPref.getBusArrivalCode;
import static ir.chichand.chichand.Tools.SharedPref.getBusArrivalName;
import static ir.chichand.chichand.Tools.SharedPref.getBusDepartureCode;
import static ir.chichand.chichand.Tools.SharedPref.getBusDepartureDate;
import static ir.chichand.chichand.Tools.SharedPref.getBusDepartureName;
import static ir.chichand.chichand.Tools.SharedPref.getBusSourceDestination;
import static ir.chichand.chichand.Tools.SharedPref.getCurrencyAmount;
import static ir.chichand.chichand.Tools.SharedPref.getCurrencyName;
import static ir.chichand.chichand.Tools.SharedPref.getCurrencyType;
import static ir.chichand.chichand.Tools.SharedPref.getFlightAmount;
import static ir.chichand.chichand.Tools.SharedPref.getFlightArrival;
import static ir.chichand.chichand.Tools.SharedPref.getFlightDeparture;
import static ir.chichand.chichand.Tools.SharedPref.getFlightDepartureDate;
import static ir.chichand.chichand.Tools.SharedPref.getFlightSourceDestination;
import static ir.chichand.chichand.Tools.SharedPref.setBusAmount;
import static ir.chichand.chichand.Tools.SharedPref.setBusArrivalCode;
import static ir.chichand.chichand.Tools.SharedPref.setBusArrivalName;
import static ir.chichand.chichand.Tools.SharedPref.setBusDepartureCode;
import static ir.chichand.chichand.Tools.SharedPref.setBusDepartureDate;
import static ir.chichand.chichand.Tools.SharedPref.setBusDepartureName;
import static ir.chichand.chichand.Tools.SharedPref.setBusSourceDestination;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyAmount;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyName;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyType;
import static ir.chichand.chichand.Tools.SharedPref.setFlightAmount;
import static ir.chichand.chichand.Tools.SharedPref.setFlightArriavl;
import static ir.chichand.chichand.Tools.SharedPref.setFlightDeparture;
import static ir.chichand.chichand.Tools.SharedPref.setFlightDepartureDate;
import static ir.chichand.chichand.Tools.SharedPref.setFlightSourceDestination;

public class AlarmListActivity extends AppCompatActivity {

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    @BindView(R.id.ll_activity_alarm_list_busAlarmHolder)
    LinearLayout ll_busAlarmHolder;

    @BindView(R.id.ll_activity_alarm_list_flightAlarmHolder)
    LinearLayout ll_flightAlarmHolder;

    @BindView(R.id.ll_activity_alarm_list_currencyAlarmHolder)
    LinearLayout ll_currencyAlarmHolder;

    @BindView(R.id.iv_activity_alarm_list_stopBus)
    ImageView iv_stopBus;

    @BindView(R.id.iv_activity_alarm_list_stopFlight)
    ImageView iv_stopFlight;

    @BindView(R.id.iv_activity_alarm_list_stopCurrency)
    ImageView iv_stopCurrency;

    @BindView(R.id.tv_activity_alarm_list_busItem)
    TextView tv_busItem;

    @BindView(R.id.tv_activity_alarm_list_flightItem)
    TextView tv_flightItem;

    @BindView(R.id.tv_activity_alarm_list_currencyItem)
    TextView tv_currencyItem;

    AlertDialog _dialogOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        ButterKnife.bind(this);
        setupactionbar();

        handleVisibility();

        iv_stopBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBusDepartureCode("");
                setBusArrivalCode("");
                setBusDepartureName("");
                setBusArrivalName("");
                setBusDepartureDate("");
                setBusSourceDestination("");
                setBusAmount(0l);
                handleVisibility();

            }
        });


        iv_stopCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setCurrencyType("");
                setCurrencyAmount(0l);
                setCurrencyName("");
                handleVisibility();

            }
        });

        iv_stopFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setFlightSourceDestination("");
                setFlightDeparture("");
                setFlightArriavl("");
                setFlightDepartureDate("");
                setFlightAmount(0l);
                handleVisibility();

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void setupactionbar() {

        tv_actionbar_title.setText("خبر از ما");

        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent_black_percent_60));

        iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void handleVisibility() {


        if (getBusDepartureCode().equals("")
                || getBusArrivalCode().equals("")
                || getBusDepartureName().equals("")
                || getBusArrivalName().equals("")
                || getBusDepartureDate().equals("")
                || getBusSourceDestination().equals("")
                || getBusAmount() == 0)
            ll_busAlarmHolder.setVisibility(View.GONE);

        if (getCurrencyType().equals("")
                || getCurrencyAmount() == 0
                || getCurrencyName().equals(""))
            ll_currencyAlarmHolder.setVisibility(View.GONE);


        if (getFlightSourceDestination().equals("")
                || getFlightDeparture().equals("")
                || getFlightArrival().equals("")
                || getFlightDepartureDate().equals("")
                || getFlightAmount() == 0)
            ll_flightAlarmHolder.setVisibility(View.GONE);

        if (ll_busAlarmHolder.getVisibility() == View.GONE
                && ll_currencyAlarmHolder.getVisibility() == View.GONE
                && ll_flightAlarmHolder.getVisibility() == View.GONE) {
            makeEmptyDialog();
            _dialogOffline.show();
        } else {

            tv_busItem.setText(getBusAmount() + "");
            tv_flightItem.setText(getFlightAmount() + "");
            tv_currencyItem.setText(getCurrencyAmount() + "");

        }
    }


    void makeEmptyDialog() {
        _dialogOffline = new AlertDialog.Builder(this)
                .setMessage("ظاهرا قرار نیست خبرتون کنیم!")
                .setCancelable(false)
                .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .create();
    }
}
