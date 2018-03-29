package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_Car implements Serializable

{


    @SerializedName("car_name")
    private String car_name;

    @SerializedName("price_bazar")
    private String price_bazar;

    @SerializedName("price")
    private String price;


    public Response_Car(String car_name, String price_bazar, String price) {
        this.car_name = car_name;
        this.price_bazar = price_bazar;
        this.price = price;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getPrice_bazar() {
        return price_bazar;
    }

    public void setPrice_bazar(String price_bazar) {
        this.price_bazar = price_bazar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
