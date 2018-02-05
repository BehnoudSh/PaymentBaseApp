package ir.chichand.chichand.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Dialog.BusSearchResultDialog;
import ir.chichand.chichand.Dialog.BusSelectCityDialog;
import ir.chichand.chichand.Dialog.Dialog_LoadingWithMessage;
import ir.chichand.chichand.Models.Requests.Request_SearchBuses;
import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.Models.Responses.Response_SearchBuses;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BusActivity extends AppCompatActivity {

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;

    @BindView(R.id.tv_activity_bus_source)
    TextView tv_source;

    @BindView(R.id.tv_activity_bus_destination)
    TextView tv_destination;

    @BindView(R.id.iv_deleteSource)
    ImageView deleteSource;

    @BindView(R.id.iv_deleteDestination)
    ImageView deleteDestination;

    @BindView(R.id.dateholder)
    LinearLayout dateholder;

    @BindView(R.id.searchBus)
    Button datehsearchBusolder;

    @BindView(R.id.datetimebus)
    TextView datetimebus;


    Response_BusCity selectedSource;

    Response_BusCity selectedDestination;

    Dialog_LoadingWithMessage dialog_loading_with_message;

    ArrayList<Response_BusCity> cities = new ArrayList<Response_BusCity>();

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


        tv_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusSelectCityDialog selectCityDialog = new BusSelectCityDialog(BusActivity.this, cities, new BusSelectCityDialog.selectCityInterface() {
                    @Override
                    public void onCitySelected(Response_BusCity selectedcity) {

                        selectedSource = selectedcity;
                        tv_source.setText(selectedSource.getPersianName());
                        tv_source.clearFocus();
                        deleteSource.setVisibility(View.VISIBLE);
                    }
                });
                selectCityDialog.show();

            }
        });


        tv_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BusSelectCityDialog selectCityDialog = new BusSelectCityDialog(BusActivity.this, cities, new BusSelectCityDialog.selectCityInterface() {
                    @Override
                    public void onCitySelected(Response_BusCity selectedcity) {
                        selectedDestination = selectedcity;
                        tv_destination.setText(selectedDestination.getPersianName());
                        tv_destination.clearFocus();
                        deleteDestination.setVisibility(View.VISIBLE);
                    }
                });
                selectCityDialog.show();
            }
        });

        deleteSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_source.setText("مبدا");
                selectedSource = null;
                deleteSource.setVisibility(View.INVISIBLE);
            }
        });

        deleteDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_destination.setText("مقصد");
                selectedDestination = null;
                deleteDestination.setVisibility(View.INVISIBLE);
            }
        });


        getBusCities();

        dateholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                PersianCalendar now = new PersianCalendar();
//                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
//                                                                                     @Override
//                                                                                     public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//                                                                                         Toast.makeText(BusActivity.this, "" + year + "/" + monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
//                                                                                     }
//                                                                                 }, now.getPersianYear(),
//                        now.getPersianMonth(),
//                        now.getPersianDay());
//
//                datePickerDialog.setThemeDark(true);
//                datePickerDialog.show(getFragmentManager(), "tpd");
            }
        });

        datehsearchBusolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        datehsearchBusolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBuses();
            }
        });


    }

    void getBusCities() {
        dialog_loading_with_message = new Dialog_LoadingWithMessage(this, "در حال دریافت لیست شهرها ...");
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

                cities.addAll(response);
                Collections.sort(response);

            }
        });
    }

    void searchBuses() {
        dialog_loading_with_message = new Dialog_LoadingWithMessage(this, "در حال دریافت لیست اتوبوس‌ها ...");
        dialog_loading_with_message.show();

        Request_SearchBuses reqeust = new Request_SearchBuses(selectedSource.getCode(), selectedDestination.getCode(), selectedSource.getName(), selectedDestination.getName(), datetimebus.getText().toString());

        ApiHandler.searchBuses(BusActivity.this, reqeust, new ApiCallbacks.searchBusesInterface() {
            @Override
            public void onSearchBusesFailed(String message) {
                dialog_loading_with_message.dismiss();

            }

            @Override
            public void onSearchBusesSucceeded(Response_SearchBuses response) {
                dialog_loading_with_message.dismiss();

                BusSearchResultDialog dialog = new BusSearchResultDialog(BusActivity.this, response);
                dialog.show();
            }
        });


    }


    void setupactionbar() {

        tv_actionbar_title.setText("بلیت اتوبوس");

        actionbarholder.setBackgroundColor(getResources().getColor(R.color.holder3));

    }

}
