package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_initializedata implements Serializable {

    @SerializedName("products")
    private Response_internet_package products;

    public Response_initializedata(Response_internet_package products) {
        this.products = products;
    }

    public Response_internet_package getProducts() {
        return products;
    }

    public void setProducts(Response_internet_package products) {
        this.products = products;
    }
}
