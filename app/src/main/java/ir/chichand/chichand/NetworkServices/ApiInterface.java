package ir.chichand.chichand.NetworkServices;


import java.util.List;

import ir.chichand.chichand.Model.Response_Categories;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;



public interface ApiInterface {


    @GET("catlist.php")
    Call<List<Response_Categories>> getCategories();


}
