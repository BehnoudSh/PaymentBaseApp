package ir.haftrang.haftrang.Tools;

import java.util.ArrayList;
import java.util.List;

import ir.haftrang.haftrang.Models.Responses.Response_BusCity;
import ir.haftrang.haftrang.Models.Responses.Response_Categories;
import ir.haftrang.haftrang.Models.Responses.Response_FlightCity;


public class PublicVariables {


    public static List<Response_Categories> allCategories = new ArrayList<>();

    public static List<Response_BusCity> allBusCities = new ArrayList<>();

    public static List<Response_FlightCity> allFlightCities = new ArrayList<>();

    public static String alarm_selectedCurrency = "";

    public static String alarm_selectedType = "";

    public static long alarm_selectedAmount = 0;

    public static long AlarmInterval = 30 * 60 * 1000; //10 minutes
}
