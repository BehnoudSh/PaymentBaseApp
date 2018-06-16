package ir.zarjame.haftrang.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 5/10/18.
 */

public class Request_Internet implements Serializable {

    @SerializedName("packageId")
    private String packageId;

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

    public Request_Internet(String packageId, String cellphone, String webserviceId, String redirectUrl, String issuer, boolean redirectToPage, String scriptVersion, String firstOutputType, String secondOutputType) {
        this.packageId = packageId;
        this.cellphone = cellphone;
        this.webserviceId = webserviceId;
        this.redirectUrl = redirectUrl;
        this.issuer = issuer;
        this.redirectToPage = redirectToPage;
        this.scriptVersion = scriptVersion;
        this.firstOutputType = firstOutputType;
        this.secondOutputType = secondOutputType;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
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
