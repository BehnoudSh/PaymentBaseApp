package ir.chichand.chichand.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Dialog.BusSearchResultDialog;
import ir.chichand.chichand.Dialog.BusSelectCityDialog;
import ir.chichand.chichand.Dialog.Dialog_LoadingWithMessage;
import ir.chichand.chichand.Dialog.FlightSearchResultDialog;
import ir.chichand.chichand.Dialog.FlightSelectCityDialog;
import ir.chichand.chichand.Models.Requests.Request_SearchBuses;
import ir.chichand.chichand.Models.Requests.Request_SearchFlights;
import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.Models.Responses.Response_FlightCity;
import ir.chichand.chichand.Models.Responses.Response_SearchBuses;
import ir.chichand.chichand.Models.Responses.Response_SearchFlights;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FlightActivity extends AppCompatActivity {

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;
    
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


    Response_FlightCity selectedSource;

    Response_FlightCity selectedDestination;

    Dialog_LoadingWithMessage dialog_loading_with_message;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    ArrayList<Response_FlightCity> cities = new ArrayList<Response_FlightCity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);


        ButterKnife.bind(this);

        setupactionbar();
        tv_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FlightSelectCityDialog selectCityDialog = new FlightSelectCityDialog(FlightActivity.this, cities, new FlightSelectCityDialog.selectCityInterface() {
                    @Override
                    public void onCitySelected(Response_FlightCity selectedcity) {

                        selectedSource = selectedcity;
                        tv_source.setText(selectedSource.getCity());
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


                FlightSelectCityDialog selectCityDialog = new FlightSelectCityDialog(FlightActivity.this, cities, new FlightSelectCityDialog.selectCityInterface() {
                    @Override
                    public void onCitySelected(Response_FlightCity selectedcity) {
                        selectedDestination = selectedcity;
                        tv_destination.setText(selectedDestination.getCity());
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
                searchFlights();
            }
        });


    }


    void getBusCities() {
        dialog_loading_with_message = new Dialog_LoadingWithMessage(this, "در حال دریافت لیست شهرها ...");
        dialog_loading_with_message.show();

        ApiHandler.getFlightCities(this, new ApiCallbacks.getFlightCitiesInterface() {
            @Override
            public void onGetFlightCitiesFailed(String message) {

            }

            @Override
            public void onGetFlightCitiesSucceeded(List<Response_FlightCity> response) {
                dialog_loading_with_message.dismiss();
                PublicVariables.allFlightCities = response;

                cities.addAll(response);
                Collections.sort(response);
            }
        });
    }

    void searchFlights() {
        dialog_loading_with_message = new Dialog_LoadingWithMessage(this, "در حال دریافت لیست پروازها ...");
        dialog_loading_with_message.show();

        Request_SearchFlights reqeust = new Request_SearchFlights(selectedSource.getIata(), selectedDestination.getIata(), datetimebus.getText().toString());

        ApiHandler.searchFlights(FlightActivity.this, reqeust, new ApiCallbacks.searchFlightsInterface() {
            @Override
            public void onSearchFlightsFailed(String message) {
                dialog_loading_with_message.dismiss();

            }

            @Override
            public void onSearchFlightsSucceeded(Response_SearchFlights response) {
                dialog_loading_with_message.dismiss();

                FlightSearchResultDialog dialog = new FlightSearchResultDialog(FlightActivity.this, response);
                dialog.show();
            }
        });


    }


    void setupactionbar() {

        tv_actionbar_title.setText("بلیت هواپیما");

        actionbarholder.setBackgroundColor(getResources().getColor(R.color.holder3));
        iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
