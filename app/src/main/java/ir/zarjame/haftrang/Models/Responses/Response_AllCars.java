package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_AllCars implements Serializable

{

    @SerializedName("company_list")
    private List<Response_CarList> company_list;


    public Response_AllCars(List<Response_CarList> company_list) {
        this.company_list = company_list;
    }

    public List<Response_CarList> getCompany_list() {
        return company_list;
    }

    public void setCompany_list(List<Response_CarList> company_list) {
        this.company_list = company_list;
    }
}
