package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_internet_package implements Serializable {

    @SerializedName("internetPackage")
    private Response_Internet_Packages internetPackage;

    public Response_internet_package(Response_Internet_Packages internetPackage) {
        this.internetPackage = internetPackage;
    }

    public Response_Internet_Packages getInternetPackage() {
        return internetPackage;
    }

    public void setInternetPackage(Response_Internet_Packages internetPackage) {
        this.internetPackage = internetPackage;
    }
}
