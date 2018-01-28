package ir.chichand.chichand.NetworkServices;

import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Config;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;

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

}
