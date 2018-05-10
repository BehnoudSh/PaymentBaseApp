package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_Charge_PaymentInfo implements Serializable

{

    @SerializedName("url")
    private String url;

    public Response_Charge_PaymentInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


