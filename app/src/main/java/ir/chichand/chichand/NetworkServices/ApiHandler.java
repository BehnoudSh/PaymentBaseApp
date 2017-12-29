package ir.chichand.chichand.NetworkServices;

import android.content.Context;

import java.util.List;

import ir.chichand.chichand.Model.Response_Categories;
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


}
