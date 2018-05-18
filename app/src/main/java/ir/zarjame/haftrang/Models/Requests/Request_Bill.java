package ir.zarjame.haftrang.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 5/10/18.
 */

public class Request_Bill implements Serializable {

    @SerializedName("billId")
    private String billId;

    @SerializedName("paymentId")
    private String paymentId;

    @SerializedName("cellphone")
    private String cellphone;

    @SerializedName("webserviceId")
    private String webserviceId;

    @SerializedName("redirectUrl")
    private String redirectUrl;

    @SerializedName("issuer")
    private String issuer;

    @SerializedName("redirectToPage")
    private boolean redirectToPage;

    @SerializedName("scriptVersion")
    private String scriptVersion;

    @SerializedName("firstOutputType")
    private String firstOutputType;

    @SerializedName("secondOutputType")
    private String secondOutputType;

    public Request_Bill(String billId, String paymentId, String cellphone, String webserviceId, String redirectUrl, String issuer, boolean redirectToPage, String scriptVersion, String firstOutputType, String secondOutputType) {
        this.billId = billId;
        this.paymentId = paymentId;
        this.cellphone = cellphone;
        this.webserviceId = webserviceId;
        this.redirectUrl = redirectUrl;
        this.issuer = issuer;
        this.redirectToPage = redirectToPage;
        this.scriptVersion = scriptVersion;
        this.firstOutputType = firstOutputType;
        this.secondOutputType = secondOutputType;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getWebserviceId() {
        return webserviceId;
    }

    public void setWebserviceId(String webserviceId) {
        this.webserviceId = webserviceId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public boolean isRedirectToPage() {
        return redirectToPage;
    }

    public void setRedirectToPage(boolean redirectToPage) {
        this.redirectToPage = redirectToPage;
    }

    public String getScriptVersion() {
        return scriptVersion;
    }

    public void setScriptVersion(String scriptVersion) {
        this.scriptVersion = scriptVersion;
    }

    public String getFirstOutputType() {
        return firstOutputType;
    }

    public void setFirstOutputType(String firstOutputType) {
        this.firstOutputType = firstOutputType;
    }

    public String getSecondOutputType() {
        return secondOutputType;
    }

    public void setSecondOutputType(String secondOutputType) {
        this.secondOutputType = secondOutputType;
    }
}
