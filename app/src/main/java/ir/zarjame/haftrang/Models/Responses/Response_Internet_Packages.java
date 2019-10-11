package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Internet_Packages implements Serializable {

    @SerializedName("mtn")
    private Response_Internet_mtn mtn;

    @SerializedName("mci")
    private Response_Internet_mci mci;

    @SerializedName("rtl")
    private Response_Internet_rtl rtl;

    public Response_Internet_Packages(Response_Internet_mtn mtn, Response_Internet_mci mci, Response_Internet_rtl rtl) {
        this.mtn = mtn;
        this.mci = mci;
        this.rtl = rtl;
    }

    public Response_Internet_mtn getMtn() {
        return mtn;
    }

    public void setMtn(Response_Internet_mtn mtn) {
        this.mtn = mtn;
    }

    public Response_Internet_mci getMci() {
        return mci;
    }

    public void setMci(Response_Internet_mci mci) {
        this.mci = mci;
    }

    public Response_Internet_rtl getRtl() {
        return rtl;
    }

    public void setRtl(Response_Internet_rtl rtl) {
        this.rtl = rtl;
    }
}
