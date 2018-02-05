package ir.chichand.chichand.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Dialog.Dialog_LoadingWithMessage;
import ir.chichand.chichand.Fragments.BusSelectCityFragment;
import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BusActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;

    @BindView(R.id.tv_activity_bus_source)
    TextView tv_source;

    @BindView(R.id.tv_activity_bus_destination)
    TextView tv_destination;

    Response_BusCity selectedSource;

    Response_BusCity selectedDestination;

    final int CODE_CITY_SOURCE = 8888, CODE_CITY_DESTINATION = 8889;

    Dialog_LoadingWithMessage dialog_loading_with_message;

    Intent cities_intent;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bus);

        ButterKnife.bind(this);

        setupactionbar();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        Fragment_BusSearch busSearchFragment = Fragment_BusSearch.newInstance();
//        fragmentTransaction.add(R.id.frame, busSearchFragment, "busSearchFragment");
//        fragmentTransaction.addToBackStack("busSearchFragment");
//        fragmentTransaction.commit();


        tv_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(cities_intent, CODE_CITY_SOURCE);
                overridePendingTransition(R.anim.come_in, R.anim.go_out);
            }
        });
        tv_source.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    startActivityForResult(cities_intent, CODE_CITY_SOURCE);
                    overridePendingTransition(R.anim.come_in, R.anim.go_out);
                }
            }
        });


        tv_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(cities_intent, CODE_CITY_DESTINATION);
                overridePendingTransition(R.anim.come_in, R.anim.go_out);
            }
        });
        tv_destination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    startActivityForResult(cities_intent, CODE_CITY_DESTINATION);
                    overridePendingTransition(R.anim.come_in, R.anim.go_out);
                }
            }
        });
        dialog_loading_with_message = new Dialog_LoadingWithMessage(this, "در حال دریافت لیست شهرها ...");
        getBusCities();
    }

    void getBusCities() {
        dialog_loading_with_message.show();

        ApiHandler.getBusCities(this, new ApiCallbacks.getBusCitiesInterface() {
            @Override
            public void onGetBusCitiesFailed(String message) {
                dialog_loading_with_message.dismiss();
            }

            @Override
            public void onGetBusCitiesSucceeded(List<Response_BusCity> response) {
                dialog_loading_with_message.dismiss();
                PublicVariables.allBusCities = response;

//                cities_intent = new Intent(BusActivity.this, BusSelectCityFragment.class);
//                cities_intent.putExtra("cities",   response);

                ArrayList<Response_BusCity> cities = new ArrayList<Response_BusCity>();

                cities.addAll(response);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Collections.sort(response);
                BusSelectCityFragment passengerDetailsFragment = BusSelectCityFragment.newInstance(cities);
                fragmentTransaction.add(R.id.frame, passengerDetailsFragment, "passenger_details");
                fragmentTransaction.addToBackStack("passenger_details");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    void setupactionbar() {

        tv_actionbar_title.setText("بلیت اتوبوس");

        actionbarholder.setBackgroundColor(getResources().getColor(R.color.holder3));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  overridePendingTransition(R.anim.come_out,R.anim.go_in);
        if ((requestCode == CODE_CITY_SOURCE || requestCode == CODE_CITY_DESTINATION) && data != null) {
            Response_BusCity city = ((Response_BusCity) data.getSerializableExtra("city"));
            if (city != null) {
                switch (requestCode) {
                    case CODE_CITY_SOURCE:
                        selectedSource = city;
                        tv_source.setText(selectedSource.getPersianName());
                        tv_source.clearFocus();
                        break;
                    case CODE_CITY_DESTINATION:
                        selectedDestination = city;
                        tv_destination.setText(selectedDestination.getPersianName());
                        tv_destination.clearFocus();
                        break;
                }
            }
        }
    }

}
