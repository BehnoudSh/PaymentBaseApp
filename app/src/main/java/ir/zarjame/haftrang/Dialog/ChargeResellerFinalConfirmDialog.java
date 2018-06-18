package ir.zarjame.haftrang.Dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Models.Requests.Request_Charge;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

public class ChargeResellerFinalConfirmDialog extends Dialog {

    private Unbinder unbinder;

    @BindView(R.id.btn_dialog_final_confirm_yes)
    Button btn_yes;

    @BindView(R.id.btn_dialog_final_confirm_no)
    Button btn_no;

    @BindView(R.id.tv_dialog_final_confirm_txtOperator)
    TextView tv_operator;

    @BindView(R.id.tv_dialog_final_confirm_txtAmount)
    TextView tv_amount;

    @BindView(R.id.tv_dialog_final_confirm_txtMobile)
    TextView tv_mobile;

    Context context;

    String operator = "";
    String amount = "";
    String mobilenumber = "";
    String selectedBank = "";
    String type = "";

    public ChargeResellerFinalConfirmDialog(@NonNull Context context,
                                            String operator,
                                            String amount,
                                            String mobilenumber,
                                            String type,
                                            String selectedbank) {

        super(context);
        this.context = context;
        this.operator = operator;
        this.amount = amount;
        this.mobilenumber = mobilenumber;
        this.type = type;
        this.selectedBank = selectedbank;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_final_confirm);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(lp);

        this.setCancelable(false);
        unbinder = ButterKnife.bind(this);


        tv_operator.setText("آیا از خرید شارژ " + operator + " به مبلغ");

        tv_amount.setText(PublicTools.getThousandSeperated(amount));

        tv_mobile.setText(mobilenumber);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Request_Charge request = new Request_Charge(type,
                        amount,
                        mobilenumber,
                        "",
                        "5a4f6a5c-3200-4811-9ada-503d5bef3768",
                        PublicTools.charge_url,
                        selectedBank,
                        true,
                        "Android",
                        "json",
                        "json");
                final ProgressDialog dialog = PublicTools.ProgressDialogInstance(context, "در حال اتصال به درگاه بانک ...");

                dialog.show();
                ApiHandler.charge(context, request, new ApiCallbacks.getChargeResponseInterface() {
                    @Override
                    public void onGetChargeFailed(String message) {
                        dialog.dismiss();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onGetChargeSucceeded(Response_ChargeReseller response) {
                        dialog.dismiss();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(response.getPaymentInfo().getUrl()));
                        context.startActivity(i);
                    }
                });
            }
        });


    }
}


