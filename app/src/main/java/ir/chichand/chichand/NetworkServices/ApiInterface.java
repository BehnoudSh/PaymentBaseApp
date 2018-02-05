package ir.chichand.chichand.NetworkServices;


import java.util.List;

import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Config;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;
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

//    @GET("bus/domestic/cities.php")
//    Call<List<Response_‌BusCity>> getBusCities();
//
//    @GET("flight/domestic/cities.php")
//    Call<List<Response_‌FlightCity>> getFlightCities();
//
//    @Headers("Content-Type: application/json")
//    @POST("flight/domestic/searchflight.php")
//    Call<Response_‌SearchFlights> searchFlights(@Body Request_SearchFlights request);
//
//    @Headers("Content-Type: application/json")
//    @POST("bus/domestic/searchbus.php")
//    Call<Response_‌SearchBuses> searchBuses(@Body Request_SearchFlights request);


}
