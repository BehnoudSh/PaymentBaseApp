package ir.zarjame.haftrang.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 5/10/18.
 */

public class Request_Charge implements Serializable {

    @SerializedName("type")
    private String type;

    @SerializedName("amount")
    private String amount;

    @SerializedName("cellphone")
    private String cellphone;

    @SerializedName("email")
    private String email;

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

//    {
//
//        "type":"MTN",
//            "amount":"1000",
//            "cellphone":"09368081516",
//            "email":"behnoud.sherafati@gmail.com",
//            "webserviceId":"5a4f6a5c-3200-4811-9ada-503d5bef3768",
//            "redirectUrl":"",
//            "issuer":"Mellat",
//            "redirectToPage":false,
//            "scriptVersion":"Android",
//            "firstOutputType":"json",
//            "secondOutputType":"json"
//
//    }


    public Request_Charge(String type, String amount, String cellphone, String email, String webserviceId, String redirectUrl, String issuer, boolean redirectToPage, String scriptVersion, String firstOutputType, String secondOutputType) {
        this.type = type;
        this.amount = amount;
        this.cellphone = cellphone;
        this.email = email;
        this.webserviceId = webserviceId;
        this.redirectUrl = redirectUrl;
        this.issuer = issuer;
        this.redirectToPage = redirectToPage;
        this.scriptVersion = scriptVersion;
        this.firstOutputType = firstOutputType;
        this.secondOutputType = secondOutputType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
