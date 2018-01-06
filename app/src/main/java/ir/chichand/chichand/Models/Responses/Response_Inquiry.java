package ir.chichand.chichand.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Inquiry implements Serializable {

    @SerializedName("has_url")
    private int has_url;

    @SerializedName("url")
    private String url;

    @SerializedName("has_data")
    private int has_data;

    @SerializedName("data_count")
    private int data_count;

    @SerializedName("data")
    private List<Response_Inquiry_Data> data;

    @SerializedName("data_group")
    private List<Response_Inquiry_Data_Group> data_group;

    public Response_Inquiry(int has_url, String url, int has_data, int data_count, List<Response_Inquiry_Data> data, List<Response_Inquiry_Data_Group> data_group) {
        this.has_url = has_url;
        this.url = url;
        this.has_data = has_data;
        this.data_count = data_count;
        this.data = data;
        this.data_group = data_group;
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

    public List<Response_Inquiry_Data> getData() {
        return data;
    }

    public void setData(List<Response_Inquiry_Data> data) {
        this.data = data;
    }

    public List<Response_Inquiry_Data_Group> getData_group() {
        return data_group;
    }

    public void setData_group(List<Response_Inquiry_Data_Group> data_group) {
        this.data_group = data_group;
    }
}
