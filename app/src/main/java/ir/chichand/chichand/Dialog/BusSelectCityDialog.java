package ir.chichand.chichand.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.chichand.chichand.Activity.BusActivity;
import ir.chichand.chichand.Adapters.Adapter_BusCities;
import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;

public class BusSelectCityDialog extends Dialog {

    @BindView(R.id.ll_actBusSelectCity_search_container)
    LinearLayout ll_search_container;
    @BindView(R.id.et_actBusSelectCity_search)
    EditText et_search;
    @BindView(R.id.ll_actBusSelectCity_search_clear)
    LinearLayout ll_search_clear;

    @BindView(R.id.rv_actBusSelectCity)
    RecyclerView recyclerView;
    @BindView(R.id.tv_actBusSelectCity_empty)
    TextView tv_empty;

    ArrayList<Response_BusCity> cities = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    private Unbinder unbinder;


    Context context;
    selectCityInterface listener;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        cities = ((List_Serializable) getIntent().getSerializableExtra("cities")).getList();
//        layoutManager = new LinearLayoutManager(this);
//        final Adapter_BusCities adapter = new Adapter_BusCities(cities, this, new Adapter_BusCities.OnItemClickListener() {
//            @Override
//            public void onItemClick(Response_BusCity city, int position) {
//                Intent intent = new Intent();
//                intent.putExtra("city", city);
//                setResult(0, intent);
//                finish();
//            }
//
//            @Override
//            public void onListEmptied() {
//                tv_empty.setVisibility(View.VISIBLE);
//                recyclerView.setVisibility(View.GONE);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(layoutManager);
//        if (cities == null || cities.size() == 0) {
//            tv_empty.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
//        }
//
//        ll_search_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                et_search.setText("");
//                // Statics.hideKeyboard(BusSelectCityDialog.this,et_search);
//            }
//        });
//
//        et_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adapter.filter(s.toString());
//                ll_search_clear.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_search.requestFocus();
//        // Statics.showKeyboard(this);
//    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//
//            cities = (ArrayList<Response_BusCity>) getArguments().getSerializable("buscities");
//
//
//        }
//    }

//    public static BusSelectCityDialog newInstance(ArrayList<Response_BusCity> buscities) {
//        BusSelectCityDialog fragment = new BusSelectCityDialog();
//        Bundle bundle = new Bundle();
//
//        bundle.putSerializable("buscities", new List_Serializable<Response_BusCity>(buscities));
//
//        fragment.setArguments(bundle);
//
//        return fragment;
//    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.fragment_bus_selectcity);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;

        this.getWindow().setAttributes(lp);

        this.setCancelable(true);
        unbinder = ButterKnife.bind(this);


        layoutManager = new LinearLayoutManager(context);

        final Adapter_BusCities adapter = new Adapter_BusCities(cities, context, new Adapter_BusCities.OnItemClickListener() {
            @Override
            public void onItemClick(Response_BusCity city, int position) {
                listener.onCitySelected(city);

                if (cities.size() != 0)
                    for (int i = 0; i < cities.size(); i++) {
                        cities.remove(i);
                    }


                cities.addAll(PublicVariables.allBusCities);
                BusSelectCityDialog.this.dismiss();
            }

            @Override
            public void onListEmptied() {
                tv_empty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        if (cities == null || cities.size() == 0) {
            tv_empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        ll_search_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                // Statics.hideKeyboard(BusSelectCityDialog.this,et_search);
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
                ll_search_clear.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_search.requestFocus();
        // Statics.showKeyboard(this);

    }


    public interface selectCityInterface {

        void onCitySelected(Response_BusCity selectedcity);

    }


    public BusSelectCityDialog(Context context, ArrayList<Response_BusCity> cities, BusSelectCityDialog.selectCityInterface listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.cities = cities;
    }

}
