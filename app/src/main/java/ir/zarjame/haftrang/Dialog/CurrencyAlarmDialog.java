package ir.zarjame.haftrang.Dialog;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Adapters.Adapter_simpleSpinner;
import ir.zarjame.haftrang.AlarmManager.CurrencyAlarmReceiver;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry_Data;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import ir.zarjame.haftrang.Tools.PublicVariables;

import static ir.zarjame.haftrang.Tools.PublicTools.getFormattedNumber;
import static ir.zarjame.haftrang.Tools.PublicVariables.AlarmInterval;
import static ir.zarjame.haftrang.Tools.SharedPref.setCurrencyAmount;
import static ir.zarjame.haftrang.Tools.SharedPref.setCurrencyName;
import static ir.zarjame.haftrang.Tools.SharedPref.setCurrencyType;

public class CurrencyAlarmDialog extends Dialog {

    private Unbinder unbinder;
    List<Response_Inquiry_Data> currencyList;
    Context context;

    @BindView(R.id.sp_dialog_currency_alarm_chooseCurrency)
    Spinner sp_currencySpinner;

    @BindView(R.id.sp_dialog_currency_alarm_chooseType)
    Spinner sp_typeSpinner;

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
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
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

                    PublicVariables.alarm_selectedAmount = Long.valueOf(alarm_amount.getText().toString().trim().replace(",", ""));

                    Intent alarm = new Intent(context, CurrencyAlarmReceiver.class);
                    boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
                    if (alarmRunning == false) {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarm, 0);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), AlarmInterval, pendingIntent);
                    }

                    setCurrencyType(PublicVariables.alarm_selectedType);
                    setCurrencyName(PublicVariables.alarm_selectedCurrency);
                    setCurrencyAmount(PublicVariables.alarm_selectedAmount);

                    dismiss();


                    Response_Inquiry_Data selectedCurrency = new Response_Inquiry_Data();
                    for (Response_Inquiry_Data currency : currencyList) {
                        if (currency.getName().equals(PublicVariables.alarm_selectedCurrency))
                            selectedCurrency = currency;

                    }

                   /* if (PublicVariables.alarm_selectedType.equals("برابر با")) {

                        if (Long.valueOf(selectedCurrency.getPrice().replace(",", "")) != PublicVariables.alarm_selectedAmount) {

                            showAlarmDialog("در حال حاضر قیمت " + selectedCurrency.getName() + " برابر با " + PublicTools.getThousandSeperated(PublicVariables.alarm_selectedAmount) + " ریال نیست ولی ما پیوسته در حال بررسی قیمت هستیم و شما را مطلع خواهیم ساخت.");
                        } else {

                            showAlarmDialog("در حال حاضر قیمت " + selectedCurrency.getName() + " برابر با " + PublicTools.getThousandSeperated(PublicVariables.alarm_selectedAmount) + " ریال هست.");

                        }

                    } else*/

                    if (PublicVariables.alarm_selectedType.equals("بیشتر از")) {
                        if (Long.valueOf(selectedCurrency.getPrice().replace(",", "")) < PublicVariables.alarm_selectedAmount) {
                            showAlarmDialog("ما پیوسته در حال بررسی قیمت هستیم و شما را مطلع خواهیم ساخت.");

                        } else {
                            showAlarmDialog("در حال حاضر قیمت " + selectedCurrency.getName() + " کمتر از " + PublicTools.getThousandSeperated(PublicVariables.alarm_selectedAmount) + " ریال هست ولی ما پیوسته در حال بررسی قیمت هستیم و شما را مطلع خواهیم ساخت.");


                        }
                    } else if (PublicVariables.alarm_selectedType.equals("کمتر از")) {
                        if (Long.valueOf(selectedCurrency.getPrice().replace(",", "")) > PublicVariables.alarm_selectedAmount) {
                            showAlarmDialog("ما پیوسته در حال بررسی قیمت هستیم و شما را مطلع خواهیم ساخت.");

                        } else {
                            showAlarmDialog("در حال حاضر قیمت " + selectedCurrency.getName() + " بیشتر از " + PublicTools.getThousandSeperated(PublicVariables.alarm_selectedAmount) + " ریال هست ولی ما پیوسته در حال بررسی قیمت هستیم و شما را مطلع خواهیم ساخت.");

                        }
                    }
                }
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        alarm_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    alarm_amount.removeTextChangedListener(this);
                    String currentText = editable.toString().trim();
                    currentText = currentText.replaceAll("٬", "").replaceAll(",", "").replaceAll("،", "");
                    if (currentText.length() > 0) {
                        alarm_amount.setText(getFormattedNumber(Long.valueOf(currentText)));
                        alarm_amount.setSelection(alarm_amount.getText().length());
                    }
                    alarm_amount.addTextChangedListener(this);
                } catch (Exception ex) {
                }
            }
        });
    }


    void showAlarmDialog(final String message) {
//        AlertDialog _dialog = new AlertDialog.Builder(context)
//                .setMessage(message)
//                .setCancelable(true)
//
//                .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss();
//
//                    }
//                })
//
//                .create();
//        _dialog.show();


        AlarmSelectedDialog dialog = new AlarmSelectedDialog(context, message);
        dialog.show();


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


