package ir.chichand.chichand.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_PhoneBill extends Response_Base implements Serializable {


    @SerializedName("data")
    private List<Response_PhoneBillData> data;




    public Response_PhoneBill(int has_url, String url, int has_data, int data_count) {
        super(has_url, url, has_data, data_count);
    }

    public Response_PhoneBill(int has_url, String url, int has_data, int data_count, List<Response_PhoneBillData> data ) {
        super(has_url, url, has_data, data_count);
        this.data = data;

    }

    public List<Response_PhoneBillData> getData() {
        return data;
    }

    public void setData(List<Response_PhoneBillData> data) {
        this.data = data;
    }


}
