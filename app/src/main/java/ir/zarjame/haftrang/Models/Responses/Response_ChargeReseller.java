package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_ChargeReseller implements Serializable

{

    @SerializedName("status")
    private String status;

    @SerializedName("orderHash")
    private String orderHash;

    @SerializedName("paymentInfo")
    private Response_Charge_PaymentInfo paymentInfo;

    @SerializedName("errorMessage")
    private String errorMessage;


    public Response_ChargeReseller(String status, String orderHash, Response_Charge_PaymentInfo paymentInfo, String errorMessage) {
        this.status = status;
        this.orderHash = orderHash;
        this.paymentInfo = paymentInfo;
        this.errorMessage = errorMessage;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}


