package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Internet_Package_Irancell implements Serializable {

    @SerializedName("mtn")
    private Response_Internet_mtn mtn;


    public Response_Internet_Package_Irancell(Response_Internet_mtn mtn) {
        this.mtn = mtn;
    }

    public Response_Internet_mtn getMtn() {
        return mtn;
    }

    public void setMtn(Response_Internet_mtn mtn) {
        this.mtn = mtn;
    }
}
