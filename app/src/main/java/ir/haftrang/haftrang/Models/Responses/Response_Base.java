package ir.haftrang.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bSherafati on 2/19/2018.
 */

public class Response_Base implements Serializable

{
    @SerializedName("has_url")
    private int has_url;

    @SerializedName("url")
    private String url;

    @SerializedName("has_data")
    private int has_data;

    @SerializedName("data_count")
    private int data_count;

    public Response_Base(int has_url, String url, int has_data, int data_count) {
        this.has_url = has_url;
        this.url = url;
        this.has_data = has_data;
        this.data_count = data_count;
    }

    public int getHas_url() {
        return has_url;
    }

    public void setHas_url(int has_url) {
        this.has_url = has_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHas_data() {
        return has_data;
    }

    public void setHas_data(int has_data) {
        this.has_data = has_data;
    }

    public int getData_count() {
        return data_count;
    }

    public void setData_count(int data_count) {
        this.data_count = data_count;
    }
}
