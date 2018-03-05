package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Inquiry extends Response_Base implements Serializable {


    @SerializedName("data")
    private List<Response_Inquiry_Data> data;

    @SerializedName("data_last_update")
    private String data_last_update;


    @SerializedName("data_group")
    private List<Response_Inquiry_Data_Group> data_group;


    public Response_Inquiry(int has_url, String url, int has_data, int data_count) {
        super(has_url, url, has_data, data_count);
    }


    public Response_Inquiry(int has_url, String url, int has_data, int data_count, List<Response_Inquiry_Data> data, String data_last_update, List<Response_Inquiry_Data_Group> data_group) {
        super(has_url, url, has_data, data_count);
        this.data = data;
        this.data_last_update = data_last_update;
        this.data_group = data_group;
    }

    public String getData_last_update() {
        return data_last_update;
    }

    public void setData_last_update(String data_last_update) {
        this.data_last_update = data_last_update;
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
