package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_Charge implements Serializable

{

    @SerializedName("status")
    private String status;

    @SerializedName("orderHash")
    private String orderHash;

    @SerializedName("paymentInfo")
    private Response_Charge_PaymentInfo paymentInfo;

    public Response_Charge(String status, String orderHash, Response_Charge_PaymentInfo paymentInfo) {
        this.status = status;
        this.orderHash = orderHash;
        this.paymentInfo = paymentInfo;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderHash() {
        return orderHash;
    }

    public void setOrderHash(String orderHash) {
        this.orderHash = orderHash;
    }

    public Response_Charge_PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(Response_Charge_PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}


