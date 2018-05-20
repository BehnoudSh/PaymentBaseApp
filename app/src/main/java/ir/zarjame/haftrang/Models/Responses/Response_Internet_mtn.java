package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Internet_mtn implements Serializable {

    @SerializedName("اینترنت ایرانسل روزانه")
    private List<Response_Internet_FinalPackage> roozane;

    @SerializedName("اینترنت ایرانسل هفتگی")
    private List<Response_Internet_FinalPackage> haftegi;

    @SerializedName("اینترنت ایرانسل ماهانه")
    private List<Response_Internet_FinalPackage> mahane;

    @SerializedName("اینترنت ایرانسل شگفت انگیز")
    private List<Response_Internet_FinalPackage> shegeftangiz;

    @SerializedName("اینترنت ایرانسل ساعتی")
    private List<Response_Internet_FinalPackage> saati;

    @SerializedName("اینترنت ثابت TDLTE")
    private List<Response_Internet_FinalPackage> tdlte;


    public Response_Internet_mtn(List<Response_Internet_FinalPackage> roozane, List<Response_Internet_FinalPackage> haftegi, List<Response_Internet_FinalPackage> mahane, List<Response_Internet_FinalPackage> shegeftangiz, List<Response_Internet_FinalPackage> saati, List<Response_Internet_FinalPackage> tdlte) {
        this.roozane = roozane;
        this.haftegi = haftegi;
        this.mahane = mahane;
        this.shegeftangiz = shegeftangiz;
        this.saati = saati;
        this.tdlte = tdlte;
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

    public List<Response_Internet_FinalPackage> getShegeftangiz() {
        return shegeftangiz;
    }

    public void setShegeftangiz(List<Response_Internet_FinalPackage> shegeftangiz) {
        this.shegeftangiz = shegeftangiz;
    }

    public List<Response_Internet_FinalPackage> getSaati() {
        return saati;
    }

    public void setSaati(List<Response_Internet_FinalPackage> saati) {
        this.saati = saati;
    }

    public List<Response_Internet_FinalPackage> getTdlte() {
        return tdlte;
    }

    public void setTdlte(List<Response_Internet_FinalPackage> tdlte) {
        this.tdlte = tdlte;
    }
}
