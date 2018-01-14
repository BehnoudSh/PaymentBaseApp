package ir.chichand.chichand.NetworkServices;

import android.content.Context;

import java.util.List;

import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;
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
                callback.getAllCategoriesSucceeded(response.body());
            }

            @Override
            public void onFailure(Call<List<Response_Categories>> call, Throwable t) {
                callback.getAllCategoriesFailed();
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
                callback.getInquirySucceeded(response.body());
            }

            @Override
            public void onFailure(Call<Response_Inquiry> call, Throwable t) {
                callback.getInquiryFailed();
            }
        });


    }


    public static void getCatLevel1_Goods(Context context, String url, final ApiCallbacks.getCatLevel1_Goods callback) {

        Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Response_Others> call = api.getCatLevel1_Goods(url);
        call.enqueue(new Callback<Response_Others>() {
            @Override
            public void onResponse(Call<Response_Others> call, Response<Response_Others> response) {
                if (response.code() < 300)
                    callback.getCatLevel0_GoodsSucceeded(response.body());
                else
                    callback.getCatLevel0_GoodsFailed();
            }

            @Override
            public void onFailure(Call<Response_Others> call, Throwable t) {
                callback.getCatLevel0_GoodsFailed();
            }
        });


    }

}
