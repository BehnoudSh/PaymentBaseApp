package ir.chichand.chichand.Dialog;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.chichand.chichand.Adapters.Adapter_simpleSpinner;
import ir.chichand.chichand.AlarmManager.AlarmReceiver;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;

import static ir.chichand.chichand.Tools.SharedPref.setAmount;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyName;
import static ir.chichand.chichand.Tools.SharedPref.setCurrencyType;

public class CurrencyAlarmDialog extends Dialog {

    private Unbinder unbinder;
    List<Response_Inquiry_Data> currencyList;
    Context context;

    @BindView(R.id.sp_dialog_currency_alarm_chooseCurrency)
    android.support.v7.widget.AppCompatSpinner sp_currencySpinner;

    @BindView(R.id.sp_dialog_currency_alarm_chooseType)
    android.support.v7.widget.AppCompatSpinner sp_typeSpinner;

    @BindView(R.id.startAlarm)
    Button bt_startAlarm;

    @BindView(R.id.et_dialog_currency_alarm_amount)
    EditText alarm_amount;

    @BindView(R.id.iv_dialog_currency_alarm_icon)
    ImageView icon;

    @BindView(R.id.iv_dialog_currency_alarm_closeDialog)
    ImageView closeDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_currency_alarm);
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


        sp_typeSpinner.getBackground().setColorFilter(context.getResources().getColor(R.color.gray_dolphin), PorterDuff.Mode.SRC_ATOP);
        sp_currencySpinner.getBackground().setColorFilter(context.getResources().getColor(R.color.gray_dolphin), PorterDuff.Mode.SRC_ATOP);


        ArrayList<String> currencyListString = new ArrayList<>();
        currencyListString.add("طلا یا ارز را انتخاب کنید ...");
        for (Response_Inquiry_Data item : this.currencyList
                ) {

            currencyListString.add(item.getName());
        }
        populateCurrencySpinner(currencyListString);

        ArrayList<String> types = new ArrayList<>();
        types.add("نوع بررسی را انتخاب کنید ...");
        types.add("بیشتر از");
        types.add("کمتر از");
        types.add("برابر با");
        populateTypeSpinner(types);

        bt_startAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PublicVariables.alarm_selectedCurrency.equals("")) {
                    Toast.makeText(context, "نوع ارز یا طلا را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(sp_currencySpinner);
                } else if (PublicVariables.alarm_selectedType.equals("")) {
                    Toast.makeText(context, "نوع بررسی را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(sp_typeSpinner);

                } else if (alarm_amount.getText().toString().equals("")) {
                    Toast.makeText(context, "قیمت را وارد نمایید", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(alarm_amount);
                } else {

                    PublicVariables.alarm_selectedAmount = Long.valueOf(alarm_amount.getText().toString());

                    Intent alarm = new Intent(context, AlarmReceiver.class);
                    boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
                    if (alarmRunning == false) {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarm, 0);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 10 * 60 * 1000, pendingIntent);
                    }

                    setCurrencyType(PublicVariables.alarm_selectedType);
                    setCurrencyName(PublicVariables.alarm_selectedCurrency);
                    setAmount(PublicVariables.alarm_selectedAmount);

                    dismiss();
                }
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public CurrencyAlarmDialog(@NonNull Context context, Context context1, List<Response_Inquiry_Data> currency_list) {
        super(context);
        this.context = context1;
        currencyList = currency_list;
    }

    void populateCurrencySpinner(final ArrayList<String> currencyListString) {

        Adapter_simpleSpinner dataAdapter = new Adapter_simpleSpinner(context, currencyListString);

        sp_currencySpinner.setAdapter(dataAdapter);

        sp_currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    PublicVariables.alarm_selectedCurrency = currencyListString.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void populateTypeSpinner(final ArrayList<String> types) {

        Adapter_simpleSpinner dataAdapter = new Adapter_simpleSpinner(context, types);

        sp_typeSpinner.setAdapter(dataAdapter);
        sp_typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    PublicVariables.alarm_selectedType = types.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


