package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Others implements Serializable {

    @SerializedName("count")
    private int count;


    @SerializedName("min_price")
    private int min_price;

    @SerializedName("max_price")
    private int max_price;

    @SerializedName("result")
    private List<Response_Others_Result> result;

//    @SerializedName("categories")
//    private result categories;


    public Response_Others(int count, int min_price, int max_price, List<Response_Others_Result> result) {
        this.count = count;
        this.min_price = min_price;
        this.max_price = max_price;
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMin_price() {
        return min_price;
    }

    public void setMin_price(int min_price) {
        this.min_price = min_price;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public List<Response_Others_Result> getResult() {
        return result;
    }

    public void setResult(List<Response_Others_Result> result) {
        this.result = result;
    }
}
