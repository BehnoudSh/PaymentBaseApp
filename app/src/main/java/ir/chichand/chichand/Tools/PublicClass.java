package ir.chichand.chichand.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Categories;


public class PublicClass {




 public  static    List<Response_Categories> allCategories = new ArrayList<>();

    public static boolean checkNetworkStatus(Context activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean internetStatus = false;
        if (connectivityManager != null) {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        internetStatus = true;
                    }
                }
            }
        }

        return internetStatus;
    }


}
