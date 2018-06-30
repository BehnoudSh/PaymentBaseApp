package ir.zarjame.haftrang.Fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Models.Requests.Request_Internet;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import ir.zarjame.haftrang.Tools.SharedPref;


public class InternetConfirmFragment extends DialogFragment {
    private Unbinder unbinder;

    String packageId = "";
    String packageDesc = "";
    String packagePrice = "";

    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;


    @BindView(R.id.btn_confirm)
    Button btn_confirm;

    @BindView(R.id.iv_fragment_charge_confirm_mellatBank)
    ImageView iv_mellatBank;

    @BindView(R.id.iv_fragment_charge_confirm_samanBank)
    ImageView iv_samanBank;

    @BindView(R.id.tv_fragment_internet_confirm_desc)
    TextView tv_desc;

    @BindView(R.id.tv_fragment_internet_confirm_price)
    TextView tv_amount;

    @BindView(R.id.et_phonenumber)
    EditText et_phonenumber;

    String selectedBank = "Mellat";

    public InternetConfirmFragment() {
        // Required empty public constructor
    }

    public static InternetConfirmFragment newInstance(String packageid, String packagedesc, String packageprice) {

        InternetConfirmFragment fragment = new InternetConfirmFragment();
        Bundle args = new Bundle();
        args.putString("packageid", packageid);
        args.putString("packagedesc", packagedesc);
        args.putString("packageprice", packageprice);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        packageId = getArguments().getString("packageid");
        packageDesc = getArguments().getString("packagedesc");
        packagePrice = getArguments().getString("packageprice");


        String numberFromCache = SharedPref.getInternetPhoneNumber();


        if (numberFromCache != null && !numberFromCache.equals(""))
            et_phonenumber.setText(numberFromCache);
        else
            et_phonenumber.setText("");

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_desc.setText(packageDesc);

        tv_amount.setText(PublicTools.getThousandSeperated(packagePrice) + " تومان");

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {

                    SharedPref.setInternetPhoneNumber(et_phonenumber.getText().toString());

                    Request_Internet request = new Request_Internet(packageId,
                            et_phonenumber.getText().toString(),
                            "5a4f6a5c-3200-4811-9ada-503d5bef3768",
                            PublicTools.internet_url,
                            selectedBank,
                            true,
                            "Android",
                            "json",
                            "get");
                    final ProgressDialog dialog = PublicTools.ProgressDialogInstance(getActivity(), "در حال اتصال به درگاه بانک ...");
                    dialog.show();
                    ApiHandler.internet(getActivity(), request, new ApiCallbacks.getInternetResponseInterface() {
                        @Override
                        public void onGetInternetFailed(String message) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onGetInternetSucceeded(Response_ChargeReseller response) {
                            dialog.dismiss();
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(response.getPaymentInfo().getUrl()));
                            startActivity(i);
                        }
                    });
                }
            }
        });

        iv_mellatBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBank = "Mellat";
                iv_mellatBank.setAlpha(1f);
                iv_samanBank.setAlpha(0.2f);

            }
        });

        iv_samanBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBank = "Saman";
                iv_mellatBank.setAlpha(0.2f);
                iv_samanBank.setAlpha(1f);

            }
        });


        et_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_phonenumber.requestFocus();
            }
        });
    }

    boolean validation() {

        if (et_phonenumber.getText().length() == 0) {
            Toast.makeText(getActivity(), "شماره موبایل را وارد نمایید", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_phonenumber);

            return false;
        }

        if (et_phonenumber.getText().length() != 11 || !et_phonenumber.getText().toString().startsWith("09")) {

            Toast.makeText(getActivity(), "شماره موبایل صحیح نیست", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_phonenumber);
            return false;

        }

        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_internet_confirm, container, false);
        unbinder = ButterKnife.bind(this, layout);


        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }

    }
}
