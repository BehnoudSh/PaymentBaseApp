package ir.chichand.chichand.NetworkServices;

import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class ApiCallbacks {

    public interface getCategoriesInterface {
        void getAllCategoriesFailed();

        void getAllCategoriesSucceeded(List<Response_Categories> response);
    }

    public interface getInquiryInterface {

        void getInquiryFailed();

        void getInquirySucceeded(Response_Inquiry response);
    }

    public interface getCatLevel1_Goods {

        void getCatLevel0_GoodsFailed();

        void getCatLevel0_GoodsSucceeded(Response_Others response);

    }

}
