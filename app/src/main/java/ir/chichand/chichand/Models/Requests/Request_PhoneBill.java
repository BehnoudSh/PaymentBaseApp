package ir.chichand.chichand.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/30/17.
 */

public class Request_PhoneBill implements Serializable {

    @SerializedName("phone_number")
    private int phone_number;

    public Request_PhoneBill(int phone_number) {
        this.phone_number = phone_number;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}
