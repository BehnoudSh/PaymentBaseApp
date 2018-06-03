package ir.zarjame.haftrang.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.zxing.common.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.zarjame.haftrang.Dialog.Dialog_Barcode_Camera;
import ir.zarjame.haftrang.Fragments.BillConfirmFragment;
import ir.zarjame.haftrang.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BillActivity extends AppCompatActivity implements Dialog_Barcode_Camera.Callback {
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.et_billId)
    EditText et_billId;

    @BindView(R.id.et_payId)
    EditText et_payId;

    @BindView(R.id.btn_confirm)
    Button btn_confirm;


    String BillId;
    String PayId;
    String BillIdPad13;
    String PayIdPad13;
    String BarCode;
    String ControllPar1;
    String ControllPar2;
    String ControllBill;
    String CheckDigitBillIdPad13;
    String CheckDigitPayIdPad13;
    String Ragham2;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);


        int color = getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary));

        String title = getIntent().getStringExtra("toolbar_title");

        setupactionbar(color, title);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {

                    BillConfirmFragment confirmFragment =
                            BillConfirmFragment.newInstance(
                                    BillIdPad13,
                                    PayIdPad13);

                    confirmFragment.show(getSupportFragmentManager(), "");
                }

            }
        });
    }

    void setupactionbar(int bg_color, String toolbar_title) {
        {
            tv_actionbar_title.setText(toolbar_title);
            toolbar.setBackgroundColor(bg_color);
            iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                        getSupportFragmentManager().popBackStack();
                    else
                        finish();

                }
            });
        }
    }


    boolean validation() {


        if (et_billId.getText().length() == 0) {
            Toast.makeText(this, "شناسه قبض را وارد نمایید", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_billId);

            return false;
        }

        if (et_payId.getText().length() == 0) {
            Toast.makeText(this, "شناسه پرداخت را وارد نمایید", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_payId);

            return false;
        }

        if (et_payId.getText().length() < 5) {
            Toast.makeText(this, "شناسه پرداخت را به درستی وارد نمایید", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_payId);

            return false;
        }


        BillIdPad13 = org.apache.commons.lang3.StringUtils.leftPad(et_billId.getText().toString().trim(), 13, '0');
        PayIdPad13 = org.apache.commons.lang3.StringUtils.leftPad(et_payId.getText().toString().trim(), 13, '0');


        BarCode = BillIdPad13 + PayIdPad13;
        ControllPar1 = BarCode.substring(24, 25);
        ControllPar2 = BarCode.substring(25, 26);
        ControllBill = BarCode.substring(12, 13);
        CheckDigitBillIdPad13 = BillIdPad13.substring(0, 12);
        CheckDigitPayIdPad13 = PayIdPad13.substring(0, 11);
        Ragham2 = BillIdPad13 + PayIdPad13.substring(0, PayIdPad13.length() - 1);


        if (!IsValidBill()) {
            Toast.makeText(this, "قبض نامعتبر", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;

    }


    @Override
    public void BarcodeCamera_onSuccess(int position, String text) {
        // beepManager.playBeepSoundAndVibrate();

        if (text.length() >= 13) {

            try {

                BillId = text.substring(0, 13);
                PayId = text.substring(13);
                et_billId.setText(BillId);
                et_payId.setText(PayId);
                if (validation()) {
                    btn_confirm.performClick();
                }


            } catch (Exception ex) {
                // showSnack(getResources().getString(R.string.invalid_info));
            }
        } else {
            // showSnack(getResources().getString(R.string.invalid_info));

        }
    }

    @Override
    public void BarcodeCamera_onCanceled() {

    }


    @OnClick(R.id.btn_barcode)
    void btbBarcodeClick(View v) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        } else {
            Dialog_Barcode_Camera dialog_barcode_camera = Dialog_Barcode_Camera.newInstance(0, true);
            dialog_barcode_camera.show(getSupportFragmentManager(), "");
        }

    }

    private boolean IsValidBill() {
        return IsValidBillId() && IsValidPayId();
    }

    private boolean IsValidBillId() {

        boolean isValidBillId = ControllBill.matches(getBillCheckDigit1(CheckDigitBillIdPad13));

        return isValidBillId;
    }

    private boolean IsValidPayId() {

        boolean isValidPayId = ControllPar1.matches(getBillCheckDigit1(CheckDigitPayIdPad13));

        return isValidPayId;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Dialog_Barcode_Camera dialog_barcode_camera = Dialog_Barcode_Camera.newInstance(0, true);
                            dialog_barcode_camera.show(getSupportFragmentManager(), "");
                        }
                    }, 500);


                } else {


                    Toast.makeText(this, "برای استفاده از اسکنر بارکد به تایید دسترسی دوربین نیاز است", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    public String getBillCheckDigit1(String input) {
        input = org.apache.commons.lang3.StringUtils.leftPad(input, 12, '0');
        int sum = 0;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(11))) * 2;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(10))) * 3;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(9))) * 4;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(8))) * 5;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(7))) * 6;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(6))) * 7;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(5))) * 2;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(4))) * 3;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(3))) * 4;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(2))) * 5;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(1))) * 6;
        sum = sum + Integer.parseInt(String.valueOf(input.charAt(0))) * 7;

        int mode = sum % 11;
        if ((mode == 0 | mode == 1)) {
            mode = 0;
        } else {
            mode = 11 - mode;
        }
        return String.valueOf(mode);
    }


}
