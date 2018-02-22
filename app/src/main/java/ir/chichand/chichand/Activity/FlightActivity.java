package ir.chichand.chichand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Dialog.BusSearchResultDialog;
import ir.chichand.chichand.Dialog.BusSelectCityDialog;
import ir.chichand.chichand.Dialog.Dialog_LoadingWithMessage;
import ir.chichand.chichand.Dialog.FlightAlarmDialog;
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
import ir.chichand.chichand.Tools.PublicTools;
import ir.chichand.chichand.Tools.PublicVariables;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FlightActivity extends AppCompatActivity {

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;
    @BindView(R.id.fab)
    FloatingActionButton fab;
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

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    Response_FlightCity selectedSource;

    Response_FlightCity selectedDestination;


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

        setupactionbar(getIntent().getStringExtra("toolbar_title"), getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary)));
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
        final PersianCalendar persianCalendar = new PersianCalendar();

        dateholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                        new com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                datetimebus.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        },
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay()
                );
                datePickerDialog.setMinDate(persianCalendar);

                datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        datetimebus.setText(persianCalendar.getPersianYear() + "-" + (persianCalendar.getPersianMonth() + 1) + "-" + persianCalendar.getPersianDay());

        datehsearchBusolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        datehsearchBusolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation())
                    searchFlights(getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary)));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {
                    FlightAlarmDialog dialog = new FlightAlarmDialog(FlightActivity.this, FlightActivity.this,
                            "پرواز " + selectedSource.getCity() + " به " + selectedDestination.getCity(),
                            "در تاریخ " + datetimebus.getText().toString() + " کمتر از ",
                            selectedSource.getIata(), selectedDestination.getIata(), datetimebus.getText().toString());
                    dialog.show();

                }
            }
        });

    }

    boolean validation() {

        if (selectedSource == null) {

            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(tv_source);
            return false;
        }

        if (selectedDestination == null) {
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(tv_destination);
            return false;
        }

        if (selectedSource.getIata().equals(selectedDestination.getIata())) {
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(tv_source);
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(tv_destination);
            return false;
        }


        return true;

    }

    void getBusCities() {
        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "در حال دریافت لیست شهرها");
        dialog.show();

        ApiHandler.getFlightCities(this, new ApiCallbacks.getFlightCitiesInterface() {
            @Override
            public void onGetFlightCitiesFailed(String message) {
                dialog.dismiss();

                Toast.makeText(FlightActivity.this, "بروز خطا، لطفا دوباره تلاش کنید", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onGetFlightCitiesSucceeded(List<Response_FlightCity> response) {
                dialog.dismiss();
                PublicVariables.allFlightCities = response;

                cities.addAll(response);
                Collections.sort(response);
            }
        });
    }

    void searchFlights(final int color) {
        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "در حال دریافت لیست پروازها");
        dialog.show();

        Request_SearchFlights request = new Request_SearchFlights(selectedSource.getIata(), selectedDestination.getIata(), datetimebus.getText().toString());

        ApiHandler.searchFlights(FlightActivity.this, request, new ApiCallbacks.searchFlightsInterface() {
            @Override
            public void onSearchFlightsFailed(String message) {
                dialog.dismiss();
                Toast.makeText(FlightActivity.this, "بروز خطا، لطفا دوباره تلاش کنید", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onSearchFlightsSucceeded(Response_SearchFlights response) {
                dialog.dismiss();
                String title = selectedSource.getCity() + " به " + selectedDestination.getCity() + " \n " + datetimebus.getText();
                if (response.getAvailable_flight().size() != 0) {
                    FlightSearchResultDialog dialog = new FlightSearchResultDialog(FlightActivity.this, response, color, title);
                    dialog.show();
                } else {


                    final AlertDialog _dialogOffline = new AlertDialog.Builder(FlightActivity.this)
                            .setMessage("از " + selectedSource.getCity() + " به " + selectedDestination.getCity() +
                                    " در تاریخ " + datetimebus.getText() + " پروازی یافت نشد! ")
                            .setCancelable(false)
                            .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            })

                            .create();

                    _dialogOffline.show();

                }
            }
        });
    }

    void setupactionbar(String title, int color) {

        tv_actionbar_title.setText(title);

        toolbar.setBackgroundColor(color);

        iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
