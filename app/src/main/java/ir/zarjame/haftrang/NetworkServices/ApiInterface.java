package ir.zarjame.haftrang.NetworkServices;


import java.util.List;

import ir.zarjame.haftrang.Models.Requests.Request_Bill;
import ir.zarjame.haftrang.Models.Requests.Request_Charge;
import ir.zarjame.haftrang.Models.Requests.Request_Inquiry;
import ir.zarjame.haftrang.Models.Requests.Request_Internet;
import ir.zarjame.haftrang.Models.Requests.Request_PhoneBill;
import ir.zarjame.haftrang.Models.Requests.Request_SearchBuses;
import ir.zarjame.haftrang.Models.Requests.Request_SearchFlights;
import ir.zarjame.haftrang.Models.Responses.Response_AllCars;
import ir.zarjame.haftrang.Models.Responses.Response_BusCity;
import ir.zarjame.haftrang.Models.Responses.Response_Categories;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.Models.Responses.Response_Config;
import ir.zarjame.haftrang.Models.Responses.Response_FlightCity;
import ir.zarjame.haftrang.Models.Responses.Response_Image;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Others;
import ir.zarjame.haftrang.Models.Responses.Response_PhoneBill;
import ir.zarjame.haftrang.Models.Responses.Response_SearchBuses;
import ir.zarjame.haftrang.Models.Responses.Response_SearchFlights;
import ir.zarjame.haftrang.Models.Responses.Response_initializedata;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET("catlist.php")
    Call<List<Response_Categories>> getCategories();

    @Headers("Content-Type: application/json")
    @POST("inquiry.php")
    Call<Response_Inquiry> getInquiry(@Body Request_Inquiry request);

    @GET
    Call<Response_Others> getCatLevel1_Goods(@Url String url);

    @GET("config.php")
    Call<Response_Config> getConfig();

    @GET("bus/domestic/cities.php")
    Call<List<Response_BusCity>> getBusCities();

    @GET("flight/domestic/cities.php")
    Call<List<Response_FlightCity>> getFlightCities();

    @Headers("Content-Type: application/json")
    @POST("flight/domestic/searchflight.php")
    Call<Response_SearchFlights> searchFlights(@Body Request_SearchFlights request);

    @Headers("Content-Type: application/json")
    @POST("bus/domestic/searchbus.php")
    Call<Response_SearchBuses> searchBuses(@Body Request_SearchBuses request);


    @Headers("Content-Type: application/json")
    @POST("billservice.php")
    Call<Response_PhoneBill> estelamPhoneBill(@Body Request_PhoneBill request);


    @Headers("Content-Type: application/json")
    @POST("billservice2.php")
    Call<Response_PhoneBill> estelamPhoneBill_miandore(@Body Request_PhoneBill request);


    @GET("car.php")
    Call<Response_AllCars> getCarPrices();


    @Headers("Content-Type: application/json")
    @POST("topup")
    Call<Response_ChargeReseller> charge(@Body Request_Charge request);

    @Headers("Content-Type: application/json")
    @POST("bill")
    Call<Response_ChargeReseller> bill(@Body Request_Bill request);

    @Headers("Content-Type: application/json")
    @POST("internetRecharge")
    Call<Response_ChargeReseller> internet(@Body Request_Internet request);

    @GET("initializeData")
    Call<Response_initializedata> chargeResellerGoods();




}
