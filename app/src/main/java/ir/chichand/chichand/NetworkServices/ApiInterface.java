package ir.chichand.chichand.NetworkServices;


import java.util.List;

import ir.chichand.chichand.Models.Reqeusts.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Categories;
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


}
