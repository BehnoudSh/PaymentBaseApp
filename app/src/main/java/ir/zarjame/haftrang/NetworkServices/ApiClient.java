package ir.zarjame.haftrang.NetworkServices;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bSherafati on 6/10/2017.
 */

public class ApiClient {

    public static final String BASE_URL_BACKEND = "http://api.zarjame.ir/";

    private static Retrofit retrofit = null;
    private static Retrofit retrofit_charge = null;

    private static OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    public static Retrofit getClient(Context context) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_BACKEND)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getClient_chargereseller(Context context) {

        if (retrofit_charge == null) {
            retrofit_charge = new Retrofit.Builder()
                    .baseUrl("https://chr724.ir/services/v3/EasyCharge/")
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit_charge;
    }

    public static Retrofit getClient_dynamicUrl(Context context, String url) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
