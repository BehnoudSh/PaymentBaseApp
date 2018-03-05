package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_PhoneBillData implements Serializable {

    @SerializedName("bill_id")
    private String bill_id;

    @SerializedName("pay_id")
    private String pay_id;

    @SerializedName("price")
    private String price;

    @SerializedName("cycle")
    private String cycle;

    @SerializedName("url")
    private String url;

    public Response_PhoneBillData(String bill_id, String pay_id, String price, String cycle, String url) {
        this.bill_id = bill_id;
        this.pay_id = pay_id;
        this.price = price;
        this.cycle = cycle;
        this.url = url;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

