package ir.zarjame.haftrang.Tools;

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

    //region CurrencyAlarmManager
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
    public static String getFlightSourceDestination() {
        return SharedPref.getInstance().mPreferences.getString("flightSourceDestination", "");

    }

    public static void setFlightSourceDestination(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("flightSourceDestination", currencyname).apply();
    }


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
    public static String getBusSourceDestination() {
        return SharedPref.getInstance().mPreferences.getString("busSourceDestination", "");

    }

    public static void setBusSourceDestination(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("busSourceDestination", currencyname).apply();
    }

    public static String getBusDepartureCode() {
        return SharedPref.getInstance().mPreferences.getString("busDepartureCode", "");

    }

    public static void setBusDepartureCode(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("busDepartureCode", currencyname).apply();
    }

    public static String getBusArrivalCode() {
        return SharedPref.getInstance().mPreferences.getString("busArrivalCode", "");

    }

    public static void setBusArrivalCode(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("busArrivalCode", currencyname).apply();
    }

    public static String getBusDepartureName() {
        return SharedPref.getInstance().mPreferences.getString("busDepartureName", "");

    }

    public static void setBusDepartureName(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("busDepartureName", currencyname).apply();
    }

    public static String getBusArrivalName() {
        return SharedPref.getInstance().mPreferences.getString("busArrivalName", "");

    }

    public static void setBusArrivalName(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("busArrivalName", currencyname).apply();
    }

    public static Long getBusAmount() {
        return SharedPref.getInstance().mPreferences.getLong("busAmount", 0);

    }

    public static void setBusAmount(Long amount) {
        SharedPref.getInstance().mPreferences.edit().putLong("busAmount", amount).apply();
    }

    public static String getBusDepartureDate() {
        return SharedPref.getInstance().mPreferences.getString("busDepartureDate", "");

    }

    public static void setBusDepartureDate(String currencyname) {
        SharedPref.getInstance().mPreferences.edit().putString("busDepartureDate", currencyname).apply();
    }


    //endregion

    public static Boolean getHelpState() {
        return SharedPref.getInstance().mPreferences.getBoolean("help", false);
    }

    public static void setHelpState() {
        SharedPref.getInstance().mPreferences.edit().putBoolean("help", true).apply();
    }

    public static String getChargePhoneNumber() {
        return SharedPref.getInstance().mPreferences.getString("chargephonenumber", "");

    }

    public static void setChargePhoneNumber(String currencytype) {
        SharedPref.getInstance().mPreferences.edit().putString("chargephonenumber", currencytype).apply();
    }

    public static String getInternetPhoneNumber() {
        return SharedPref.getInstance().mPreferences.getString("internetphonenumber", "");

    }

    public static void setInternetPhoneNumber(String currencytype) {
        SharedPref.getInstance().mPreferences.edit().putString("internetphonenumber", currencytype).apply();
    }

    public static String getBillPhoneNumber() {
        return SharedPref.getInstance().mPreferences.getString("billphonenumber", "");

    }

    public static void setBillPhoneNumber(String currencytype) {
        SharedPref.getInstance().mPreferences.edit().putString("billphonenumber", currencytype).apply();
    }

}
