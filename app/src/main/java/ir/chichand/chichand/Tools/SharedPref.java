package ir.chichand.chichand.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;


public class SharedPref {

    Context mContext;
    public SharedPreferences mPreferences;

    private static SharedPref reference = null;

    public static SharedPref getInstance() {
        if (reference == null) {
            reference = new SharedPref();
        }
        return reference;
    }

    public SharedPref() {

    }

    public void initSharedPref(Context context) {
        mContext = context;
        mPreferences = new SecurePreferences(mContext);
    }


    public static String getCurrencyName() {
        return SharedPref.getInstance().mPreferences.getString("currencyName", "");

    }

    public static void setCurrencyName(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("currencyName", currencyname).apply();
    }


    public static String getCurrencyType() {
        return SharedPref.getInstance().mPreferences.getString("currencyType", "");

    }

    public static void setCurrencyType(String currencytype) {
        SharedPref.getInstance().mPreferences.edit().putString("currencyType", currencytype).apply();
    }


    public static Long getAmount() {
        return SharedPref.getInstance().mPreferences.getLong("amount", 0);

    }

    public static void setAmount(Long amount) {
        SharedPref.getInstance().mPreferences.edit().putLong("amount", amount).apply();
    }

}
