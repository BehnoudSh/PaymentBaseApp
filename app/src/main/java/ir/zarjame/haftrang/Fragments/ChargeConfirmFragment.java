package ir.zarjame.haftrang.Fragments;


import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Adapters.Adapter_simpleSpinner;
import ir.zarjame.haftrang.Dialog.ChargeResellerFinalConfirmDialog;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.SharedPref;

import static ir.zarjame.haftrang.Models.Operators.IRANCELL;
import static ir.zarjame.haftrang.Models.Operators.MCI;
import static ir.zarjame.haftrang.Models.Operators.RIGHTEL;
import static ir.zarjame.haftrang.Tools.PublicTools.getFormattedNumber;


public class ChargeConfirmFragment extends DialogFragment {
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

    String persianOperator = "";

    public ChargeConfirmFragment() {
        // Required empty public constructor
    }

    public static ChargeConfirmFragment newInstance(String selectedtype,
                                                    String selected_operator,
                                                    String selected_operator_persian) {

        ChargeConfirmFragment fragment = new ChargeConfirmFragment();
        Bundle args = new Bundle();
        args.putString("selectedoperator", selected_operator);
        args.putString("selectedoperator_persian", selected_operator_persian);
        args.putString("selectedtype", selectedtype);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getArguments().getString("selectedtype");
        operator = getArguments().getString("selectedoperator");
        persianOperator = getArguments().getString("selectedoperator_persian");


    }

    void populateTypeSpinner(final ArrayList<String> types) {

        Adapter_simpleSpinner dataAdapter = new Adapter_simpleSpinner(getActivity(), types);

        sp_price.setAdapter(dataAdapter);
        sp_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPrice = "";
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

        String numberFromCache = SharedPref.getChargePhoneNumber();


        if (numberFromCache != null && !numberFromCache.equals("")) {
            et_mobile.setText(numberFromCache);
            et_mobile.setSelection(et_mobile.getText().length());

        } else {
            et_mobile.setText("");

        }


        if (operator.equals(IRANCELL.getStringValueEnglish())) {
            ll_irancelPriceHolder.setVisibility(View.VISIBLE);
            tv_unitTitle.setVisibility(View.GONE);

        } else {
            sp_price.setVisibility(View.VISIBLE);
            sp_price.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.gray_dolphin), PorterDuff.Mode.SRC_ATOP);

            ArrayList<String> types = new ArrayList<>();
            types.add("???????? ???????? ???? ???????????? ???????? (??????????) ...");

            if (!type.equals("RTL!"))
                types.add("??,??????");

            types.add("??,??????");
            types.add("??,??????");
            types.add("????,??????");
            types.add("????,??????");

            populateTypeSpinner(types);

        }

        if (operator.equals(IRANCELL.getStringValueEnglish())) {
            ll_parent.setBackgroundResource(R.drawable.irancellgradiant);

            if (type.equals("MTN")) {
                tv_title.setText("???????? ????????????????? ?????????????? ??????????????");
            } else if (type.equals("MTN!")) {
                tv_title.setText("???????? ????????????????????? ????????????????? ?????????????? ??????????????");

            } else if (type.equals("MTN#")) {
                tv_title.setText("???????? ????????????????? ?????????? ??????????????");

            } else if (type.equals("WiMax")) {
                tv_title.setText("???????? ???????? ???????????? ??????????????");

            }


        } else if (operator.equals(MCI.getStringValueEnglish())) {
            ll_parent.setBackgroundResource(R.drawable.mcigradiant);
            if (type.equals("MCI")) {
                tv_title.setText("???????? ????????????????? ?????????????? ?????????? ??????");
            }


        } else if (operator.equals(RIGHTEL.getStringValueEnglish())) {
            ll_parent.setBackgroundResource(R.drawable.rightelgradiant);

            if (type.equals("RTL")) {
                tv_title.setText("???????? ????????????????? ?????????????? ??????????");
            } else if (type.equals("RTL!")) {
                tv_title.setText("???????? ???????????????? ????????????????? ?????????????? ??????????");
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
                    currentText = currentText.replaceAll("??", "").replaceAll(",", "").replaceAll("??", "");
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

                    SharedPref.setChargePhoneNumber(et_mobile.getText().toString());

                    ChargeResellerFinalConfirmDialog dialog = new ChargeResellerFinalConfirmDialog(
                            getActivity(),
                            persianOperator,
                            selectedPrice.replace(",", ""),
                            et_mobile.getText().toString(),
                            type,
                            selectedBank

                    );
                    dialog.show();


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
            Toast.makeText(getActivity(), "?????????? ???????????? ???? ???????? ????????????", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_mobile);

            return false;
        }

        if (et_mobile.getText().length() != 11 || !et_mobile.getText().toString().startsWith("09")) {

            Toast.makeText(getActivity(), "?????????? ???????????? ???????? ????????", Toast.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(et_mobile);
            return false;

        }

        if (operator.equals(IRANCELL.getStringValueEnglish())) {
            if (type.equals("MTN") || type.equals("MTN!") || type.equals("MTN#")) {
                if (!et_mobile.getText().toString().startsWith("090") && !et_mobile.getText().toString().startsWith("093")) {
                    Toast.makeText(getActivity(), "?????????? ???????????? ?????????????? ???????? ????????", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_mobile);
                    return false;
                }
            } else if (type.equals("WiMax")) {

                if (!et_mobile.getText().toString().startsWith("094")) {
                    Toast.makeText(getActivity(), "?????????? ???????????? ???????????? ?????????????? ???????? ????????", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_mobile);
                    return false;
                }

            }


            if (et_priceirancell.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "???????? ???????? ?????????????? ???? ???????? ????????????", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_priceirancell);

                return false;
            }

            if (Integer.parseInt(selectedPrice) < 1000) {
                Toast.makeText(getActivity(), "???????? ???????? ?????????????? ?????????? ??,?????? ?????????? ???????????????", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_priceirancell);

                return false;
            }


            if (Integer.parseInt(selectedPrice) > 200000) {
                Toast.makeText(getActivity(), "???????? ???????? ?????????????? ???????????? ??????,?????? ?????????? ???????????????", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_priceirancell);

                return false;
            }


        } else if (operator.equals(MCI.getStringValueEnglish())) {


            if (!et_mobile.getText().toString().startsWith("091") && !et_mobile.getText().toString().startsWith("0990")) {
                Toast.makeText(getActivity(), "?????????? ???????????? ?????????? ?????? ???????? ????????", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_mobile);
                return false;
            }

            if (selectedPrice.equals("")) {
                Toast.makeText(getActivity(), "???????? ???????? ?????????? ?????? ???? ???????????? ????????????", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(sp_price);
                return false;


            }

        } else if (operator.equals(RIGHTEL.getStringValueEnglish())) {
            if (!et_mobile.getText().toString().startsWith("0921") && !et_mobile.getText().toString().startsWith("0922")) {
                Toast.makeText(getActivity(), "?????????? ???????????? ?????????? ???????? ????????", Toast.LENGTH_SHORT).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .playOn(et_mobile);
                return false;

            }

            if (selectedPrice.equals("")) {
                Toast.makeText(getActivity(), "???????? ???????? ?????????? ???? ???????????? ????????????", Toast.LENGTH_SHORT).show();
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
