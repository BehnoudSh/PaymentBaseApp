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

    @BindView(R.id.estelamBill_payandore)
    Button btn_estelamBill_payandore;

    @BindView(R.id.estelamBill_miandore)
    Button btn_estelamBill_miandore;

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

        btn_estelamBill_payandore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (et_phoneNumber.getText().toString().trim().equals("") || et_phoneNumber.getText().toString().trim().length() != 11) {

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_phoneNumber);
                    return;
                }


                final ProgressDialog dialog = PublicTools.ProgressDialogInstance(context, "در حال استعلام بدهی پایان‌دوره");
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
                        try {

                            String message = "";
                            if (!response.getData().get(0).getPrice().equals("0")) {
                                message = "دوره " + response.getData().get(0).getCycle()
                                        + "\n"
                                        + "مبلغ " + PublicTools.getThousandSeperated(response.getData().get(0).getPrice()) + " ریال "
                                        + "\n";
                                makeOfflineDialog(message, response.getData().get(0).getBill_id(), response.getData().get(0).getPay_id(), false);

                            } else {
                                message = "دوره " + response.getData().get(0).getCycle()
                                        + "\n"
                                        + "مبلغ " + PublicTools.getThousandSeperated(response.getData().get(0).getPrice()) + " ریال ";
                                makeOfflineDialog(message, response.getData().get(0).getBill_id(), response.getData().get(0).getPay_id(), true);

                            }


                            _dialogOffline.show();

                            setConfirmSize();
                        } catch (Exception ex) {

                            Toast.makeText(context, "بروز خطا در استعلام، دوباره تلاش کنید", Toast.LENGTH_LONG).show();

                        }


                    }
                });
            }
        });

        btn_estelamBill_miandore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (et_phoneNumber.getText().toString().trim().equals("") || et_phoneNumber.getText().toString().trim().length() != 11) {

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_phoneNumber);
                    return;
                }


                final ProgressDialog dialog = PublicTools.ProgressDialogInstance(context, "در حال استعلام بدهی میان‌دوره");
                dialog.show();

                Request_PhoneBill request = new Request_PhoneBill(et_phoneNumber.getText().toString().trim());

                ApiHandler.estelamPhoneBill_miandore(context, request, new ApiCallbacks.estelamPhoneBillInterface() {
                    @Override
                    public void onestelamPhoneBillFailed(String message) {
                        dialog.dismiss();
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onestelamPhoneBillSucceeded(Response_PhoneBill response) {
                        dialog.dismiss();
                        try {
                            String message = "";
                            if (!response.getData().get(0).getPrice().equals("0")) {
                                message = "دوره " + response.getData().get(0).getCycle()
                                        + "\n"
                                        + "مبلغ " + PublicTools.getThousandSeperated(response.getData().get(0).getPrice()) + " ریال "
                                        + "\n";
                                makeOfflineDialog(message, response.getData().get(0).getBill_id(), response.getData().get(0).getPay_id(), false);

                            } else {
                                message = "دوره " + response.getData().get(0).getCycle()
                                        + "\n"
                                        + "مبلغ " + PublicTools.getThousandSeperated(response.getData().get(0).getPrice()) + " ریال ";
                                makeOfflineDialog(message, response.getData().get(0).getBill_id(), response.getData().get(0).getPay_id(), true);

                            }


                            _dialogOffline.show();

                            setConfirmSize();
                        } catch (Exception ex) {
                            Toast.makeText(context, "بروز خطا در استعلام، دوباره تلاش کنید", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }

    void setConfirmSize() {
        try {
            Button btnPositive = _dialogOffline.getButton(Dialog.BUTTON_POSITIVE);
            btnPositive.setBackgroundResource(R.drawable.bg_rounded_color2);
            btnPositive.setTextColor(context.getResources().getColor(R.color.gray_dolphin));
//            btnPositive.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.default_txt_size));
        } catch (Exception ex) {
        }
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
                    .setNegativeButton("بستن", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    })
                    .setPositiveButton("پرداخت این قبض", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            String billidpad13 = org.apache.commons.lang3.StringUtils.leftPad(billid, 13, '0');
                            String payidpad13 = org.apache.commons.lang3.StringUtils.leftPad(payid, 13, '0');

                            BillConfirmFragment confirmFragment = BillConfirmFragment.newInstance(billidpad13, payidpad13);

                            confirmFragment.show(((MainActivity) context).getSupportFragmentManager(), "");
                        }
                    })


                    .create();


        } else

        {
            _dialogOffline = new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(false)
                    .setNegativeButton("بستن", new DialogInterface.OnClickListener() {
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
