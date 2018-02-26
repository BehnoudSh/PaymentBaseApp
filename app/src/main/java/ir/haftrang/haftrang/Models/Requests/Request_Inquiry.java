package ir.haftrang.haftrang.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/30/17.
 */

public class Request_Inquiry implements Serializable {

    @SerializedName("cat_id")
    private int cat_id;

    @SerializedName("query")
    private String query;

    public Request_Inquiry(int cat_id, String query) {
        this.cat_id = cat_id;
        this.query = query;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
