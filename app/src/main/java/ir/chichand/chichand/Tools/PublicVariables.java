package ir.chichand.chichand.Tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_FlightCity;


public class PublicVariables {


    public static List<Response_Categories> allCategories = new ArrayList<>();

    public static List<Response_BusCity> allBusCities = new ArrayList<>();

    public static List<Response_FlightCity> allFlightCities = new ArrayList<>();

    public static String alarm_selectedCurrency = "";

    public static String alarm_selectedType = "";

    public static long alarm_selectedAmount = 0;

    public static long AlarmInterval = 10000; //10 seconds
}
