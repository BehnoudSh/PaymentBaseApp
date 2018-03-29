package ir.zarjame.haftrang.NetworkServices;

import android.content.Context;

import java.util.List;

import ir.zarjame.haftrang.Models.Requests.Request_Inquiry;
import ir.zarjame.haftrang.Models.Requests.Request_PhoneBill;
import ir.zarjame.haftrang.Models.Requests.Request_SearchBuses;
import ir.zarjame.haftrang.Models.Requests.Request_SearchFlights;
import ir.zarjame.haftrang.Models.Responses.Response_AllCars;
import ir.zarjame.haftrang.Models.Responses.Response_BusCity;
import ir.zarjame.haftrang.Models.Responses.Response_Categories;
import ir.zarjame.haftrang.Models.Responses.Response_Config;
import ir.zarjame.haftrang.Models.Responses.Response_FlightCity;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Others;
import ir.zarjame.haftrang.Models.Responses.Response_PhoneBill;
import ir.zarjame.haftrang.Models.Responses.Response_SearchBuses;
import ir.zarjame.haftrang.Models.Responses.Response_SearchFlights;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class ApiHandler {


    public static void getAllCategories(Context context, final ApiCallbacks.getCategoriesInterface callback) {
        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<Response_Categories>> call = api.getCategories();
        call.enqueue(new Callback<List<Response_Categories>>() {
            @Override
            public void onResponse(Call<List<Response_Categories>> call, Response<List<Response_Categories>> response) {
                callback.onGetAllCategoriesSucceeded(response.body());
            }

            @Override
            public void onFailure(Call<List<Response_Categories>> call, Throwable t) {
                callback.onGetAllCategoriesFailed();
            }
        });


    }


    public static void getInquiry(Context context, Request_Inquiry request, final ApiCallbacks.getInquiryInterface callback) {
        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_Inquiry> call = api.getInquiry(request);
        call.enqueue(new Callback<Response_Inquiry>() {
            @Override
            public void onResponse(Call<Response_Inquiry> call, Response<Response_Inquiry> response) {
                callback.onGetInquirySucceeded(response.body());
            }

            @Override
            public void onFailure(Call<Response_Inquiry> call, Throwable t) {
                callback.onGetInquiryFailed();
            }
        });


    }


    public static void getCatLevel1_Goods(Context context, String url, final ApiCallbacks.getCatLevel1GoodsInterface callback) {

        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_Others> call = api.getCatLevel1_Goods(url);
        call.enqueue(new Callback<Response_Others>() {
            @Override
            public void onResponse(Call<Response_Others> call, Response<Response_Others> response) {
                if (response.code() < 300)
                    callback.onGetCatLevel0_GoodsSucceeded(response.body());
                else
                    callback.onGetCatLevel0_GoodsFailed();
            }

            @Override
            public void onFailure(Call<Response_Others> call, Throwable t) {
                callback.onGetCatLevel0_GoodsFailed();
            }
        });
    }


    public static void getConfig(Context context, final ApiCallbacks.getConfigInterface callback) {
        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_Config> call = api.getConfig();
        call.enqueue(new Callback<Response_Config>() {
            @Override
            public void onResponse(Call<Response_Config> call, Response<Response_Config> response) {
                if (response.code() < 300)
                    callback.onGetConfigSucceeded(response.body());
                else
                    callback.onGetConfigFailed();
            }

            @Override
            public void onFailure(Call<Response_Config> call, Throwable t) {
                callback.onGetConfigFailed();
            }
        });


    }


    public static void getBusCities(Context context, final ApiCallbacks.getBusCitiesInterface callback)

    {
        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<Response_BusCity>> call = api.getBusCities();
        call.enqueue(new Callback<List<Response_BusCity>>() {
            @Override
            public void onResponse(Call<List<Response_BusCity>> call, Response<List<Response_BusCity>> response) {
                if (response.code() < 300)
                    callback.onGetBusCitiesSucceeded(response.body());
                else
                    callback.onGetBusCitiesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");
            }

            @Override
            public void onFailure(Call<List<Response_BusCity>> call, Throwable t) {
                callback.onGetBusCitiesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");
            }
        });


    }

    public static void getFlightCities(Context context, final ApiCallbacks.getFlightCitiesInterface callback)

    {

        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<Response_FlightCity>> call = api.getFlightCities();
        call.enqueue(new Callback<List<Response_FlightCity>>() {
            @Override
            public void onResponse(Call<List<Response_FlightCity>> call, Response<List<Response_FlightCity>> response) {

                if (response.code() < 300)
                    callback.onGetFlightCitiesSucceeded(response.body());
                else
                    callback.onGetFlightCitiesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");


            }

            @Override
            public void onFailure(Call<List<Response_FlightCity>> call, Throwable t) {
                callback.onGetFlightCitiesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");

            }
        });


    }

    public static void searchBuses(Context context, Request_SearchBuses request, final ApiCallbacks.searchBusesInterface callback)

    {

        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_SearchBuses> call = api.searchBuses(request);
        call.enqueue(new Callback<Response_SearchBuses>() {
            @Override
            public void onResponse(Call<Response_SearchBuses> call, Response<Response_SearchBuses> response) {
                if (response.code() < 300)
                    callback.onSearchBusesSucceeded(response.body());
                else
                    callback.onSearchBusesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");
            }

            @Override
            public void onFailure(Call<Response_SearchBuses> call, Throwable t) {
                callback.onSearchBusesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");
            }
        });

    }


    public static void searchFlights(Context context, Request_SearchFlights request, final ApiCallbacks.searchFlightsInterface callback)

    {

        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_SearchFlights> call = api.searchFlights(request);
        call.enqueue(new Callback<Response_SearchFlights>() {
            @Override
            public void onResponse(Call<Response_SearchFlights> call, Response<Response_SearchFlights> response) {
                if (response.code() < 300)
                    callback.onSearchFlightsSucceeded(response.body());
                else
                    callback.onSearchFlightsFailed("بروز خطا در ارتباط، دوباره تلاش کنید");
            }

            @Override
            public void onFailure(Call<Response_SearchFlights> call, Throwable t) {
                callback.onSearchFlightsFailed("بروز خطا در ارتباط، دوباره تلاش کنید");

            }
        });
    }

    public static void estelamPhoneBill(Context context, Request_PhoneBill request, final ApiCallbacks.estelamPhoneBillInterface callback) {
        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_PhoneBill> call = api.estelamPhoneBill(request);
        call.enqueue(new Callback<Response_PhoneBill>() {
            @Override
            public void onResponse(Call<Response_PhoneBill> call, Response<Response_PhoneBill> response) {
                if (response.code() < 300)

                    callback.onestelamPhoneBillSucceeded(response.body());

                else

                    callback.onestelamPhoneBillFailed("بروز خطا در ارتباط، دوباره تلاش کنید");

            }

            @Override
            public void onFailure(Call<Response_PhoneBill> call, Throwable t) {

                callback.onestelamPhoneBillFailed("بروز خطا در ارتباط، دوباره تلاش کنید");

            }
        });
    }


    public static void getCarPrices(Context context, final ApiCallbacks.getCarsInterface callback) {
        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_AllCars> call = api.getCarPrices();


        call.enqueue(new Callback<Response_AllCars>() {
            @Override
            public void onResponse(Call<Response_AllCars> call, Response<Response_AllCars> response) {
                if (response.code() < 300)

                    callback.onGetCarPricesSucceeded(response.body());

                else

                    callback.onGetCarPricesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");
            }

            @Override
            public void onFailure(Call<Response_AllCars> call, Throwable t) {
                callback.onGetCarPricesFailed("بروز خطا در ارتباط، دوباره تلاش کنید");

            }
        });


    }


}
