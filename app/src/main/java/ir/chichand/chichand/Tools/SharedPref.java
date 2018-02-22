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

    //region currencyAlarmManager
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


    public static Long getCurrencyAmount() {
        return SharedPref.getInstance().mPreferences.getLong("currencyAmount", 0);

    }

    public static void setCurrencyAmount(Long amount) {
        SharedPref.getInstance().mPreferences.edit().putLong("currencyAmount", amount).apply();
    }
    //endregion


    //region FlightAlarmManager
    public static String getFlightDeparture() {
        return SharedPref.getInstance().mPreferences.getString("flightDeparture", "");

    }

    public static void setFlightDeparture(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("flightDeparture", currencyname).apply();
    }

    public static String getFlightArrival() {
        return SharedPref.getInstance().mPreferences.getString("flightArrival", "");

    }

    public static void setFlightArriavl(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("flightArrival", currencyname).apply();
    }

    public static String getFlightDepartureDate() {
        return SharedPref.getInstance().mPreferences.getString("flightDepartureDate", "");

    }

    public static void setFlightDepartureDate(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("flightDepartureDate", currencyname).apply();
    }


    public static Long getFlightAmount() {
        return SharedPref.getInstance().mPreferences.getLong("flightAmount", 0);

    }

    public static void setFlightAmount(Long amount) {
        SharedPref.getInstance().mPreferences.edit().putLong("flightAmount", amount).apply();
    }

    //endregion

    //region BusAlarmManager

    //endregion

}
