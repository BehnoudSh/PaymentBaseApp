package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Internet_mci implements Serializable {

    @SerializedName("اینترنت همراه اول روزانه")
    private List<Response_Internet_FinalPackage> roozane;

    @SerializedName("اینترنت همراه اول هفتگی")
    private List<Response_Internet_FinalPackage> haftegi;

    @SerializedName("اینترنت همراه اول ماهانه")
    private List<Response_Internet_FinalPackage> mahane;

    public Response_Internet_mci(List<Response_Internet_FinalPackage> roozane, List<Response_Internet_FinalPackage> haftegi, List<Response_Internet_FinalPackage> mahane) {
        this.roozane = roozane;
        this.haftegi = haftegi;
        this.mahane = mahane;
    }

    public List<Response_Internet_FinalPackage> getRoozane() {
        return roozane;
    }

    public void setRoozane(List<Response_Internet_FinalPackage> roozane) {
        this.roozane = roozane;
    }

    public List<Response_Internet_FinalPackage> getHaftegi() {
        return haftegi;
    }

    public void setHaftegi(List<Response_Internet_FinalPackage> haftegi) {
        this.haftegi = haftegi;
    }

    public List<Response_Internet_FinalPackage> getMahane() {
        return mahane;
    }

    public void setMahane(List<Response_Internet_FinalPackage> mahane) {
        this.mahane = mahane;
    }
}
