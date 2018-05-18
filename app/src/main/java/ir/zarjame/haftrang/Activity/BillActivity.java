package ir.zarjame.haftrang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.zarjame.haftrang.Models.Requests.Request_Bill;
import ir.zarjame.haftrang.Models.Requests.Request_Charge;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BillActivity extends AppCompatActivity {
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
                    Request_Bill request = new Request_Bill(et_billId.getText().toString().trim(),
                            et_payId.getText().toString().trim(),
                            "09368081516",
                            "5a4f6a5c-3200-4811-9ada-503d5bef3768",
                            "",
                            "Saman",
                            true,
                            "Android",
                            "json",
                            "json");
                    final ProgressDialog dialog = PublicTools.ProgressDialogInstance(BillActivity.this, "در حال دریافت اطلاعات ...");
                    dialog.show();
                    ApiHandler.bill(BillActivity.this, request, new ApiCallbacks.getBillResponseInterface() {
                        @Override
                        public void onGetBillFailed(String message) {
                            dialog.dismiss();
                            Toast.makeText(BillActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onGetBillSucceeded(Response_ChargeReseller response) {
                            dialog.dismiss();
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(response.getPaymentInfo().getUrl()));
                            startActivity(i);
                        }
                    });
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

        return true;

    }

}
