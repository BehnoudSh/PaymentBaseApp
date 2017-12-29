package ir.chichand.chichand.NetworkServices;

import java.util.List;

import ir.chichand.chichand.Model.Response_Categories;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public   class ApiCallbacks {


    public  interface getCategoriesInterface
    {
        void getAllCategoriesFailed();

        void getAllCategoriesSucceeded(List<Response_Categories> response);
    }

}
