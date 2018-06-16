package ir.zarjame.haftrang.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Adapters.Adapter_simpleSpinner;
import ir.zarjame.haftrang.Models.Requests.Request_Charge;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

import static ir.zarjame.haftrang.Models.Operators.IRANCELL;
import static ir.zarjame.haftrang.Models.Operators.MCI;
import static ir.zarjame.haftrang.Models.Operators.RIGHTEL;
import static ir.zarjame.haftrang.Tools.PublicTools.getFormattedNumber;


public class ChargeConfirmFragment extends BottomSheetDialogFragment {
    private Unbinder unbinder;
    String type;
    String operator;

    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;

    @BindView(R.id.title)
    TextView tv_title;

    @BindView(R.id.et_priceirancellUnit)
    TextView tv_unitTitle;


    @BindView(R.id.et_mobile)
    EditText et_mobile;

    @BindView(R.id.et_priceirancell)
    EditText et_priceirancell;

    @BindView(R.id.sp_price)
    Spinner sp_price;

    @BindView(R.id.btn_confirm)
    Button btn_confirm;

    @BindView(R.id.iv_fragment_charge_confirm_mellatBank)
    ImageView iv_mellatBank;

    @BindView(R.id.iv_fragment_charge_confirm_samanBank)
    ImageView iv_samanBank;

    @BindView(R.id.et_priceirancellHolder)
    LinearLayout ll_irancelPriceHolder;

    String selectedBank = "Mellat";

    String selectedPrice = "";

    public ChargeConfirmFragment() {
        // Required empty public constructor
    }

    public static ChargeConfirmFragment newInstance(String selectedtype, String selected_operator) {

        ChargeConfirmFragment fragment = new ChargeConfirmFragment();
        Bundle args = new Bundle();
        args.putString("selectedoperator", selected_operator);
        args.putString("selectedtype", selectedtype);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getArguments().getString("selectedtype");
        operator = getArguments().getString("selectedoperator");

    }

    void populateTypeSpinner(final ArrayList<String> types) {

        Adapter_simpleSpinner dataAdapter = new Adapter_simpleSpinner(getActivity(), types);

        sp_price.setAdapter(dataAdapter);
        sp_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    selectedPrice = types.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_mobile.requestFocus();

        if (operator.equals(IRANCELL.getStringValueEnglish())) {
            ll_irancelPriceHolder.setVisibility(View.VISIBLE);
            tv_unitTitle.setVisibility(View.GONE);

        } else {
            sp_price.setVisibility(View.VISIBLE);
            sp_price.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.gray_dolphin), PorterDuff.Mode.SRC_ATOP);

            ArrayList<String> types = new ArrayList<>();
            types.add("مبلغ شارژ را انتخاب کنید (تومان) ...");

            if (!type.equals("RTL!"))
                types.add("۱,۰۰۰");

            types.add("۲,۰۰۰");
            types.add("۵,۰۰۰");
            types.add("۱۰,۰۰۰");
            types.add("۲۰,۰۰۰");

            populateTypeSpinner(types);

        }

        if (operator.equals(IRANCELL.getStringValueEnglish())) {
            ll_parent.setBackgroundResource(R.drawable.irancellgradiant);

            if (type.equals("MTN")) {
                tv_title.setText("شارژ سیم‌کارت اعتباری ایرانسل");
            } else if (type.equals("MTN!")) {
                tv_title.setText("شارژ شگفت‌انگیز سیم‌کارت اعتباری ایرانسل");

            } else if (type.equals("MTN#")) {
                tv_title.setText("شارژ سیم‌کارت دائمی ایرانسل");

            } else if (type.equals("WiMax")) {
                tv_title.setText("شارژ مودم وایمکس ایرانسل");

            }


        } else if (operator.equals(MCI.getStringValueEnglish())) {
            ll_parent.setBackgroundResource(R.drawable.mcigradiant);
            if (type.equals("MCI")) {
                tv_title.setText("شارژ سیم‌کارت اعتباری همراه اول");
            }


        } else if (operator.equals(RIGHTEL.getStringValueEnglish())) {
            ll_parent.setBackgroundResource(R.drawable.rightelgradiant);

            if (type.equals("RTL")) {
                tv_title.setText("شارژ سیم‌کارت اعتباری رایتل");
            } else if (type.equals("RTL!")) {
                tv_title.setText("شارژ شورانگیز سیم‌کارت اعتباری رایتل");
            }
        }

        et_priceirancell.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {

                    if (editable.length() == 0)
                        tv_unitTitle.setVisibility(View.GONE);
                    else
                        tv_unitTitle.setVisibility(View.VISIBLE);


                    et_priceirancell.removeTextChangedListener(this);
                    String currentText = editable.toString().trim();
                    currentText = currentText.replaceAll("٬", "").replaceAll(",", "").replaceAll("،", "");
                    if (currentText.length() > 0) {
                        et_priceirancell.setText(getFormattedNumber(Long.valueOf(currentText)));
                        et_priceirancell.setSelection(et_priceirancell.getText().length());
                    }
                    et_priceirancell.addTextChangedListener(this);

                    selectedPrice = editable.toString().replace(",", "");

                } catch (Exception ex) {
                }
            }
        });


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {

                    Request_Charge request = new Request_Charge(type,
                            selectedPrice.replace(",", ""),
                            et_mobile.getText().toString(),
                            "",
                            "5a4f6a5c-3200-4811-9ada-503d5bef3768",
                            PublicTools.charge_url,
                            selectedBank,
                            true,
                            "Android",
                            "json",
                            "json");
                    final ProgressDialog dialog = PublicTools.ProgressDialogInstance(getActivity(), "در حال اتصال به درگاه بانک ...");

                    dialog.show();
                    ApiHandler.charge(getActivity(), request, new ApiCallbacks.getChargeResponseInterface() {
                        @Override
                        public void onGetChargeFailed(String message) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onGetChargeSucceeded(Response_ChargeReseller response) {
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
    }

    boolean validation() {

        if (et_mobile.getText().length() == 0) {
            Toast.makeText(getActivity(), "شماره موبایل را وارد نمایید", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_mobile);

            return false;
        }

        if (et_mobile.getText().length() != 11 || !et_mobile.getText().toString().startsWith("09")) {

            Toast.makeText(getActivity(), "شماره موبایل صحیح نیست", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_mobile);
            return false;

        }

        if (operator.equals(IRANCELL.getStringValueEnglish())) {
            if (type.equals("MTN") || type.equals("MTN!") || type.equals("MTN#")) {
                if (!et_mobile.getText().toString().startsWith("090") && !et_mobile.getText().toString().startsWith("093")) {
                    Toast.makeText(getActivity(), "شماره موبایل ایرانسل صحیح نیست", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_mobile);
                    return false;
                }
            } else if (type.equals("WiMax")) {

                if (!et_mobile.getText().toString().startsWith("094")) {
                    Toast.makeText(getActivity(), "شماره موبایل وایمکس ایرانسل صحیح نیست", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_mobile);
                    return false;
                }

            }


            if (et_priceirancell.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "مبلغ شارژ ایرانسل را وارد نمایید", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_priceirancell);

                return false;
            }

            if (Integer.parseInt(selectedPrice) < 1000) {
                Toast.makeText(getActivity(), "مبلغ شارژ ایرانسل حداقل ۱,۰۰۰ تومان می‌باشد", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_priceirancell);

                return false;
            }


            if (Integer.parseInt(selectedPrice) > 200000) {
                Toast.makeText(getActivity(), "مبلغ شارژ ایرانسل حداکثر ۲۰۰,۰۰۰ تومان می‌باشد", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_priceirancell);

                return false;
            }


        } else if (operator.equals(MCI.getStringValueEnglish())) {


            if (!et_mobile.getText().toString().startsWith("091") && !et_mobile.getText().toString().startsWith("0990")) {
                Toast.makeText(getActivity(), "شماره موبایل همراه اول صحیح نیست", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_mobile);
                return false;
            }

            if (selectedPrice.equals("")) {
                Toast.makeText(getActivity(), "مبلغ شارژ همراه اول را انتخاب نمایید", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(sp_price);
                return false;


            }

        } else if (operator.equals(RIGHTEL.getStringValueEnglish())) {
            if (!et_mobile.getText().toString().startsWith("0921") && !et_mobile.getText().toString().startsWith("0922")) {
                Toast.makeText(getActivity(), "شماره موبایل رایتل صحیح نیست", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_mobile);
                return false;

            }

            if (selectedPrice.equals("")) {
                Toast.makeText(getActivity(), "مبلغ شارژ رایتل را انتخاب نمایید", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(sp_price);
                return false;


            }
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_charge_confirm, container, false);
        unbinder = ButterKnife.bind(this, layout);

        return layout;
    }

}
