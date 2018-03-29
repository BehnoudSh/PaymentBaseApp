package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_CarList implements Serializable

{


    @SerializedName("car_company")
    private String car_company;


    @SerializedName("car_list")
    private List<Response_Car> car_list;


    public Response_CarList(String car_company, List<Response_Car> car_list) {
        this.car_company = car_company;
        this.car_list = car_list;
    }

    public String getCar_company() {
        return car_company;
    }

    public void setCar_company(String car_company) {
        this.car_company = car_company;
    }

    public List<Response_Car> getCar_list() {
        return car_list;
    }

    public void setCar_list(List<Response_Car> car_list) {
        this.car_list = car_list;
    }
}
