package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Inquiry_Data implements Serializable {

    @SerializedName("name")
    private String name;


    @SerializedName("price")
    private String price;


    @SerializedName("group")
    private int group;

    public Response_Inquiry_Data( ) {

    }

    public Response_Inquiry_Data(String name, String price, int group) {
        this.name = name;
        this.price = price;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
