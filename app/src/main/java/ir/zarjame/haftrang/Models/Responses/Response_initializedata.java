package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_initializedata implements Serializable {

    @SerializedName("products")
    private Response_Internet_Package_Irancell products;

    public Response_initializedata(Response_Internet_Package_Irancell products) {
        this.products = products;
    }

    public Response_Internet_Package_Irancell getProducts() {
        return products;
    }

    public void setProducts(Response_Internet_Package_Irancell products) {
        this.products = products;
    }
}
