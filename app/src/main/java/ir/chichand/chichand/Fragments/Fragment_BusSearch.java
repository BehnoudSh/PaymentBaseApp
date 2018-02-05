package ir.chichand.chichand.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.chichand.chichand.R;


public class Fragment_BusSearch extends Fragment implements DatePickerDialog.OnDateSetListener {
    private Unbinder unbinder;
//    @BindView(R.id.tv_activity_bus_datePicked)
//    TextView tv_date;
//
//    @BindView(R.id.ll_activity_bus_datePicked)
//    LinearLayout ll_dateHolder;
//
//    @BindView(R.id.iv_activity_bus_clearDestination)
//    ImageView iv_clearDestination;
//
//    @BindView(R.id.iv_activity_bus_clearSource)
//    ImageView iv_clearSource;
//
//    @BindView(R.id.et_activity_bus_source)
//    EditText et_source;
//
//    @BindView(R.id.et_activity_bus_destination)
//    EditText et_destination;
//
//    @BindView(R.id.bt_activity_bus_search)
//    Button bt_search;
//
//    String pickedDate_pretty = "";
//    String pickedDate = "";
//
//    Response_Bus_City selectedSource;
//    Response_Bus_City selectedDestination;
//
//    ArrayList<Response_Bus_City> allCitiesList;
//    Dialog_Loading dialog_loading;
//
//    Intent cities_intent;
//
//    final int CODE_CITY_SOURCE = 8888, CODE_CITY_DESTINATION = 8889;

    public static Fragment_BusSearch newInstance() {
        Fragment_BusSearch fragment = new Fragment_BusSearch();
        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);

        return fragment;
    }

    public Fragment_BusSearch() {
        // Required empty public constructor
    }

//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        getActivity().overridePendingTransition(R.anim.come_out,R.anim.go_in);
//        if((requestCode==CODE_CITY_SOURCE || requestCode==CODE_CITY_DESTINATION) && data!=null) {
//            Response_Bus_City city = ((Response_Bus_City) data.getSerializableExtra("city"));
//            if(city!=null) {
//                switch (requestCode){
//                    case CODE_CITY_SOURCE:
//                        selectedSource = city;
//                        et_source.setText(selectedSource.getName());
//                        et_source.setSelection(et_source.getText().length());
//                        et_source.clearFocus();
//                        break;
//                    case CODE_CITY_DESTINATION:
//                        selectedDestination = city;
//                        et_destination.setText(selectedDestination.getName());
//                        et_destination.setSelection(et_destination.getText().length());
//                        et_destination.clearFocus();
//                        break;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        dialog_loading = new Dialog_Loading(getActivity());
//
//        ApiClient.Bus_Token = "";
//
//        getToken();
//
//        et_source.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(cities_intent,CODE_CITY_SOURCE);
//                getActivity().overridePendingTransition(R.anim.come_in,R.anim.go_out);
//            }
//        });
//        et_source.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    startActivityForResult(cities_intent,CODE_CITY_SOURCE);
//                    getActivity().overridePendingTransition(R.anim.come_in,R.anim.go_out);
//                }
//            }
//        });
//
//        et_destination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(cities_intent,CODE_CITY_DESTINATION);
//                getActivity().overridePendingTransition(R.anim.come_in,R.anim.go_out);
//            }
//        });
//        et_destination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    startActivityForResult(cities_intent,CODE_CITY_DESTINATION);
//                    getActivity().overridePendingTransition(R.anim.come_in,R.anim.go_out);
//                }
//            }
//        });
//
//        PersianCalendar persianCalendar = new PersianCalendar();
//        onDateSet(null, persianCalendar.getPersianYear(), persianCalendar.getPersianMonth(), persianCalendar.getPersianDay());
//        ll_dateHolder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
//                        Fragment_BusSearch.this,
//                        persianCalendar.getPersianYear(),
//                        persianCalendar.getPersianMonth(),
//                        persianCalendar.getPersianDay()
//                );
//                datePickerDialog.setThemeDark(true);
//                datePickerDialog.setMinDate(persianCalendar);
//                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
//            }
//        });
//
//        iv_clearDestination.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedDestination = null;
//                et_destination.setText("");
//                iv_clearDestination.setVisibility(View.INVISIBLE);
//                et_source.clearFocus();
//            }
//        });
//
//        iv_clearSource.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedSource = null;
//                et_source.setText("");
//                iv_clearSource.setVisibility(View.INVISIBLE);
//                et_source.clearFocus();
//            }
//        });
//
//        bt_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (validate()) { //go to available buses fragment
//
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    Fragment_Available_Buses availableBusesFragment = Fragment_Available_Buses.newInstance(selectedSource, selectedDestination, pickedDate, pickedDate_pretty);
//                    fragmentTransaction.replace(R.id.frame, availableBusesFragment,"availableBusesFragment");
//                    fragmentTransaction.addToBackStack("availableBusesFragment");
//                    fragmentTransaction.commit();
//
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(bt_search.getWindowToken(), 0);
//
//                }
//            }
//        });
//
//        et_destination.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                selectedDestination = null;
//
//                if (s.length() == 0)
//                    iv_clearDestination.setVisibility(View.GONE);
//                else
//                    iv_clearDestination.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        et_source.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                selectedSource = null;
//
//                if (s.length() == 0)
//                    iv_clearSource.setVisibility(View.GONE);
//                else
//                    iv_clearSource.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//
//
//        int realMonth = monthOfYear + 1;
//        pickedDate = year + "-" + realMonth + "-" + dayOfMonth;
//
//        String month = "";
//
//        switch (monthOfYear) {
//            case 0:
//                month = "فروردین";
//                break;
//
//            case 1:
//                month = "اردیبهشت";
//                break;
//
//            case 2:
//                month = "خرداد";
//                break;
//
//            case 3:
//                month = "تیر";
//                break;
//
//            case 4:
//                month = "مرداد";
//                break;
//
//            case 5:
//                month = "شهریور";
//                break;
//
//            case 6:
//                month = "مهر";
//                break;
//
//            case 7:
//                month = "آبان";
//                break;
//
//            case 8:
//                month = "آذر";
//                break;
//
//            case 9:
//                month = "دی";
//                break;
//
//            case 10:
//                month = "بهمن";
//                break;
//
//            case 11:
//                month = "اسفند";
//                break;
//        }
//
//        String date = dayOfMonth + " " + month + " " + year;
//        pickedDate_pretty = date;
//        tv_date.setText(date);
//    }
//
//    boolean validate() {
//
//        if (et_source.getText().toString().equals("")) {
//            showSnack_Red("مبدا سفر را انتخاب کنید");
//            shake(et_source);
//            return false;
//        }
//
//        if (et_destination.getText().toString().equals("")) {
//            showSnack_Red("مقصد سفر را انتخاب کنید");
//            shake(et_destination);
//            return false;
//        }
//
//        if (pickedDate_pretty.equals("")) {
//            showSnack_Red("تاریخ سفر را وارد کنید");
//            shake(ll_dateHolder);
//            return false;
//        }
//
//        for (Response_Bus_City city : allCitiesList
//                ) {
//            if (city.getName().equals(et_source.getText().toString())) {
//
//                selectedSource = city;
//
//            }
//
//            if (city.getName().equals(et_destination.getText().toString())) {
//
//                selectedDestination = city;
//
//            }
//        }
//
//        if (selectedSource == null) {
//            showSnack_Red("مبدا سفر را درست انتخاب کنید");
//            shake(et_source);
//            et_source.requestFocus();
//            return false;
//        }
//
//        if (selectedDestination == null) {
//            showSnack_Red("مقصد سفر را درست انتخاب کنید");
//            shake(et_destination);
//            et_destination.requestFocus();
//            return false;
//        }
//
//        if (selectedSource.getID().equals(selectedDestination.getID())) {
//            showSnack_Red("مبدا و مقصد یکسان هستند");
//            shake(et_destination);
//            shake(et_source);
//            return false;
//
//        }
//
//        return true;
//    }
//
//    private void shake(View view) {
//        YoYo.with(Techniques.Shake).duration(500).playOn(view);
//    }
//
//    public void showSnack_Red(String message) {
//        Snackbar snackbar = Snackbar
//                .make(getActivity().findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
//
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundResource(R.color.red_error);
//        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setTextColor(getResources().getColor(R.color.white));
//
//        snackbar.show();
//    }
//
//
//    void getToken() {
//        dialog_loading.show();
//
//
//        ApiHandler.busToken(getActivity(), new ApiCallbacks.BusTokenCallBack() {
//            @Override
//            public void onBusTokenFailed(String message) {
//                dialog_loading.dismiss();
//                Dialog_Message dialog_message = new Dialog_Message(getActivity(), message, "تلاش مجدد", true, new Dialog_Message.MessageDialogCallback() {
//                    @Override
//                    public void onMessageDialogConfirm() {
//                        getToken();
//                    }
//
//                    @Override
//                    public void onMessageDialogCancel() {
//                        getActivity().finish();
//                        getActivity().overridePendingTransition(R.anim.come_out, R.anim.go_in);
//                    }
//
//                });
//                dialog_message.show();
//            }
//
//            @Override
//            public void onBusTokenSuccess(Response_Bus_Token response) {
//
//                ApiClient.Bus_Token = response.getAccess_token();
//                getBusCities();
//            }
//        });
//
//
//    }
//
//
//    void getBusCities() {
//        dialog_loading.show();
//
//
//        ApiHandler.busCities(getActivity(), new ApiCallbacks.BusCitiesCallBack() {
//            @Override
//            public void onBusCitiesFailed(String message) {
//                dialog_loading.dismiss();
//                Dialog_Message dialog_message = new Dialog_Message(getActivity(), message, "تلاش مجدد", true, new Dialog_Message.MessageDialogCallback() {
//                    @Override
//                    public void onMessageDialogConfirm() {
//                        getBusCities();
//                    }
//
//                    @Override
//                    public void onMessageDialogCancel() {
//                        getActivity().finish();
//                        getActivity().overridePendingTransition(R.anim.come_out, R.anim.go_in);
//                    }
//
//                });
//                dialog_message.show();
//
//            }
//
//            @Override
//            public void onBusCitiesSuccess(ArrayList<Response_Bus_City> response) {
//                dialog_loading.dismiss();
//                allCitiesList = response;
//                Collections.sort(allCitiesList);
//                /*Adapter_AutoCompleteList_BusCities adapter_autoCompleteList_source = new Adapter_AutoCompleteList_BusCities(getActivity(), response);
//                et_source.setAdapter(adapter_autoCompleteList_source);
//                et_source.setThreshold(1);*/
//
//                /*Adapter_AutoCompleteList_BusCities adapter_autoCompleteList_destination = new Adapter_AutoCompleteList_BusCities(getActivity(), response);
//                et_destination.setAdapter(adapter_autoCompleteList_destination);
//                et_destination.setThreshold(1);*/
//
//                cities_intent = new Intent(getContext(),Activity_BusSelectCity.class);
//                cities_intent.putExtra("cities",new List_Serializable<Response_Bus_City>(response));
//            }
//        });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_bus_search, container, false);
        unbinder = ButterKnife.bind(this, layout);

        return layout;

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
