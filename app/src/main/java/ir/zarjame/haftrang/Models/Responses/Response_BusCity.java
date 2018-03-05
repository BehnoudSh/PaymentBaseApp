package ir.zarjame.haftrang.Models.Responses;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bSherafati on 2/5/2018.
 */

public class Response_BusCity implements Serializable, Comparable<Response_BusCity> {

    @SerializedName("ID")
    private int ID;

    @SerializedName("Code")
    private String Code;
    @SerializedName("Name")
    private String Name;


    @SerializedName("PersianName")
    private String PersianName;

    @SerializedName("ProvincePersianName")
    private String ProvincePersianName;

    public Response_BusCity(int ID, String code, String name, String persianName, String provincePersianName) {
        this.ID = ID;
        Code = code;
        Name = name;
        PersianName = persianName;
        ProvincePersianName = provincePersianName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPersianName() {
        return PersianName;
    }

    public void setPersianName(String persianName) {
        PersianName = persianName;
    }

    public String getProvincePersianName() {
        return ProvincePersianName;
    }

    public void setProvincePersianName(String provincePersianName) {
        ProvincePersianName = provincePersianName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int compareTo(@NonNull Response_BusCity o) {
        return getPersianName().compareTo(o.getPersianName());
    }
}
