package ir.zarjame.haftrang.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Activity.ChargeActivity;
import ir.zarjame.haftrang.Models.Operators;
import ir.zarjame.haftrang.R;


public class ChargeCategoriesFragment extends Fragment {

    @BindView(R.id.mcicatsholder)
    LinearLayout mci_catsHolder;

    @BindView(R.id.irancellcatsholder)
    LinearLayout irancel_catsHolder;

    @BindView(R.id.rightelcatsholder)
    LinearLayout rightel_catsHolder;

    @BindView(R.id.irancell1)
    CardView irancell1;

    @BindView(R.id.irancell2)
    CardView irancell2;

    @BindView(R.id.irancell3)
    CardView irancell3;

    @BindView(R.id.irancell4)
    CardView irancell4;

    @BindView(R.id.mci1)
    CardView mci1;

    @BindView(R.id.rightel1)
    CardView rightel1;

    @BindView(R.id.rightel2)
    CardView rightel2;

    String selectedOperator = "";

    private Unbinder unbinder;

    public ChargeCategoriesFragment() {
    }

    public static ChargeCategoriesFragment newInstance(String operator) {

        ChargeCategoriesFragment fragment = new ChargeCategoriesFragment();
        Bundle args = new Bundle();
        args.putString("selectedoperator", operator);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedOperator = getArguments().getString("selectedoperator");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (selectedOperator.equals(Operators.IRANCELL.getStringValueEnglish())) {
            irancel_catsHolder.setVisibility(View.VISIBLE);
        } else if (selectedOperator.equals(Operators.MCI.getStringValueEnglish())) {
            mci_catsHolder.setVisibility(View.VISIBLE);
        } else if (selectedOperator.equals(Operators.RIGHTEL.getStringValueEnglish())) {
            rightel_catsHolder.setVisibility(View.VISIBLE);
        }


        irancell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("MTN", Operators.IRANCELL);
            }
        });
        irancell2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("MTN!", Operators.IRANCELL);
            }
        });
        irancell3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("MTN#", Operators.IRANCELL);
            }
        });
        irancell4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("WiMax", Operators.IRANCELL);
            }
        });

        mci1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("MCI", Operators.MCI);

            }
        });

        rightel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("RTL", Operators.RIGHTEL);

            }
        });

        rightel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment("RTL!", Operators.RIGHTEL);

            }
        });


    }

    public void addFragment(String selectedtype, Operators selected_operator) {

//        Bundle bundle = new Bundle();
//
//        bundle.putString(selectedtype, "selectedtype");
//
//        bundle.putString(selected_operator.getStringValueEnglish(), "selectedoperator");
//
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//        fragmentTransaction.add(R.id.fragmentholder, ChargeConfirmFragment.newInstance(selectedtype, selected_operator.getStringValueEnglish()));
//
//        fragmentTransaction.addToBackStack("chargeconfirmfragment");
//
//        fragmentTransaction.commit();


        ChargeConfirmFragment confirmFragment = ChargeConfirmFragment.newInstance(selectedtype, selected_operator.getStringValueEnglish());

        confirmFragment.show(((ChargeActivity) getActivity()).getSupportFragmentManager(), "");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_charge_categories, container, false);
        unbinder = ButterKnife.bind(this, layout);

        return layout;
    }
}
