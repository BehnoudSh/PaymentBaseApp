package ir.zarjame.haftrang.Dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Activity.BillActivity;
import ir.zarjame.haftrang.Activity.MainActivity;
import ir.zarjame.haftrang.Fragments.BillConfirmFragment;
import ir.zarjame.haftrang.Models.Requests.Request_Bill;
import ir.zarjame.haftrang.Models.Requests.Request_PhoneBill;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.Models.Responses.Response_PhoneBill;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

public class EstelamPhoneBillDialog extends Dialog {


    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;
    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    @BindView(R.id.estelamBill)
    Button btn_estelamBill;

    @BindView(R.id.et_activity_cat_level2_search)
    EditText et_phoneNumber;

    AlertDialog _dialogOffline;

    private Unbinder unbinder;

    Context context;
    int bg_color;
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_estelam_phone_bill);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;

        this.getWindow().setAttributes(lp);

        this.setCancelable(true);
        unbinder = ButterKnife.bind(this);

        setupactionbar(bg_color, title);

        btn_estelamBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (et_phoneNumber.getText().toString().trim().equals("") || et_phoneNumber.getText().toString().trim().length() != 11) {

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_phoneNumber);
                    return;
                }

//                if (!et_phoneNumber.getText().toString().trim().startsWith("021")) {
//                    Toast.makeText(context, "در حال حاضر فقط سرویس استعلام برای تلفن ثابت تهران موجود است", Toast.LENGTH_LONG).show();
//                    return;
//                }


                final ProgressDialog dialog = PublicTools.ProgressDialogInstance(context, "در حال استعلام مبلغ قبض تلفن ثابت");
                dialog.show();

                Request_PhoneBill request = new Request_PhoneBill(et_phoneNumber.getText().toString().trim());

                ApiHandler.estelamPhoneBill(context, request, new ApiCallbacks.estelamPhoneBillInterface() {
                    @Override
                    public void onestelamPhoneBillFailed(String message) {
                        dialog.dismiss();
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onestelamPhoneBillSucceeded(Response_PhoneBill response) {
                        dialog.dismiss();
                        String message = "";
                        if (!response.getData().get(0).getPrice().equals("0")) {
                            message = "دوره " + response.getData().get(0).getCycle()
                                    + "\n"
                                    + "مبلغ " + PublicTools.getThousandSeperated(response.getData().get(0).getPrice()) + " ریال "
                                    + "\n";
//                                    + "شناسه قبض " + response.getData().get(0).getBill_id()
//                                    + "\n"
//                                    + "شناسه پرداخت " + response.getData().get(0).getPay_id();
                            makeOfflineDialog(message, response.getData().get(0).getBill_id(), response.getData().get(0).getPay_id(), false);

                        } else {
                            message = "دوره " + response.getData().get(0).getCycle()
                                    + "\n"
                                    + "مبلغ " + PublicTools.getThousandSeperated(response.getData().get(0).getPrice()) + " ریال ";
                            makeOfflineDialog(message, response.getData().get(0).getBill_id(), response.getData().get(0).getPay_id(), true);

                        }


                        _dialogOffline.show();

                    }
                });
            }
        });
    }


    public EstelamPhoneBillDialog(Context context, int bgcolor, String title) {
        super(context);
        this.context = context;

        this.bg_color = bgcolor;
        this.title = title;
    }


    void makeOfflineDialog(String message, final String billid, final String payid, boolean zero) {

        if (!zero) {
            _dialogOffline = new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(false)
                    .setNegativeButton("باشه", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    })
                    .setPositiveButton("پرداخت قبض", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            String billidpad13 = org.apache.commons.lang3.StringUtils.leftPad(billid, 13, '0');
                            String payidpad13 = org.apache.commons.lang3.StringUtils.leftPad(payid, 13, '0');

                            BillConfirmFragment confirmFragment = BillConfirmFragment.newInstance(billidpad13, payidpad13);

                            confirmFragment.show(((MainActivity) context).getSupportFragmentManager(), "");

//                            Request_Bill request = new Request_Bill(billid,
//                                    payid,
//                                    "09368081516",
//                                    "5a4f6a5c-3200-4811-9ada-503d5bef3768",
//                                    PublicTools.bill_url,
//                                    "Saman",
//                                    true,
//                                    "Android",
//                                    "json",
//                                    "json");
//                            final ProgressDialog dialog_payment = PublicTools.ProgressDialogInstance(context, "در حال دریافت اطلاعات ...");
//                            dialog_payment.show();
//                            ApiHandler.bill(context, request, new ApiCallbacks.getBillResponseInterface() {
//                                @Override
//                                public void onGetBillFailed(String message) {
//                                    dialog_payment.dismiss();
//                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onGetBillSucceeded(Response_ChargeReseller response) {
//                                    dialog_payment.dismiss();
//                                    Intent i = new Intent(Intent.ACTION_VIEW);
//                                    i.setData(Uri.parse(response.getPaymentInfo().getUrl()));
//                                    context.startActivity(i);
//                                }
//                            });


                        }
                    })

                    .create();
        } else

        {
            _dialogOffline = new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(false)
                    .setNegativeButton("باشه", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    }).create();
        }
    }


    void setupactionbar(int color, String title) {

        tv_actionbar_title.setText(title);

        actionbarholder.setBackgroundColor(color);

        iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
