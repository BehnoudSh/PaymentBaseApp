package ir.zarjame.haftrang.NetworkServices;

import java.util.List;

import ir.zarjame.haftrang.Models.Responses.Response_AllCars;
import ir.zarjame.haftrang.Models.Responses.Response_BusCity;
import ir.zarjame.haftrang.Models.Responses.Response_Categories;
import ir.zarjame.haftrang.Models.Responses.Response_ChargeReseller;
import ir.zarjame.haftrang.Models.Responses.Response_Config;
import ir.zarjame.haftrang.Models.Responses.Response_FlightCity;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Others;
import ir.zarjame.haftrang.Models.Responses.Response_PhoneBill;
import ir.zarjame.haftrang.Models.Responses.Response_SearchBuses;
import ir.zarjame.haftrang.Models.Responses.Response_SearchFlights;
import ir.zarjame.haftrang.Models.Responses.Response_initializedata;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class ApiCallbacks {
    public interface getCarsInterface {

        void onGetCarPricesFailed(String message);

        void onGetCarPricesSucceeded(Response_AllCars response);
    }


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

    public interface getChargeResponseInterface {

        void onGetChargeFailed(String message);

        void onGetChargeSucceeded(Response_ChargeReseller response);

    }

    public interface getBillResponseInterface {

        void onGetBillFailed(String message);

        void onGetBillSucceeded(Response_ChargeReseller response);

    }

    public interface getInternetResponseInterface {

        void onGetInternetFailed(String message);

        void onGetInternetSucceeded(Response_ChargeReseller response);

    }

    public interface getBusCitiesInterface {

        void onGetBusCitiesFailed(String message);

        void onGetBusCitiesSucceeded(List<Response_BusCity> response);


    }


    public interface getFlightCitiesInterface {

        void onGetFlightCitiesFailed(String message);

        void onGetFlightCitiesSucceeded(List<Response_FlightCity> response);


    }

    public interface searchBusesInterface {


        void onSearchBusesFailed(String message);

        void onSearchBusesSucceeded(Response_SearchBuses response);


    }

    public interface searchFlightsInterface {


        void onSearchFlightsFailed(String message);

        void onSearchFlightsSucceeded(Response_SearchFlights response);


    }

    public interface estelamPhoneBillInterface {


        void onestelamPhoneBillFailed(String message);

        void onestelamPhoneBillSucceeded(Response_PhoneBill response);


    }

    public interface initializeDataInterface {


        void oninitializeDataFailed(String message);

        void oninitializeDataSucceeded(Response_initializedata response);


    }

}
