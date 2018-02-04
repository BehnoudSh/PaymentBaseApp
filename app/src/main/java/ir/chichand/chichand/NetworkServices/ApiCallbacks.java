package ir.chichand.chichand.NetworkServices;

import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Config;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;
import ir.chichand.chichand.Models.Responses.Response_‌BusCity;
import ir.chichand.chichand.Models.Responses.Response_‌FlightCity;
import ir.chichand.chichand.Models.Responses.Response_‌SearchBuses;
import ir.chichand.chichand.Models.Responses.Response_‌SearchFlights;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class ApiCallbacks {

    public interface getCategoriesInterface {

        void onGetAllCategoriesFailed();

        void onGetAllCategoriesSucceeded(List<Response_Categories> response);
    }

    public interface getInquiryInterface {

        void onGetInquiryFailed();

        void onGetInquirySucceeded(Response_Inquiry response);
    }

    public interface getCatLevel1GoodsInterface {

        void onGetCatLevel0_GoodsFailed();

        void onGetCatLevel0_GoodsSucceeded(Response_Others response);
    }

    public interface getConfigInterface {

        void onGetConfigFailed();

        void onGetConfigSucceeded(Response_Config response);

    }

    public interface getBusCitiesInterface {

        void onGetBusCitiesFailed(String message);

        void onGetBusCitiesSucceeded(List<Response_‌BusCity> response);


    }


    public interface getFlightCitiesInterface {

        void onGetFlightCitiesFailed(String message);

        void onGetFlightCitiesSucceeded(List<Response_‌FlightCity> response);


    }

    public interface searchBusesInterface {


        void onSearchBusesFailed(String message);

        void onSearchBusesSucceeded(Response_‌SearchBuses response);


    }

    public interface searchFlightsInterface {


        void onSearchFlightsFailed(String message);

        void onSearchFlightsSucceeded(Response_‌SearchFlights response);


    }

}
