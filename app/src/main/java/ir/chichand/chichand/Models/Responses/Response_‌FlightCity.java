package ir.chichand.chichand.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_‌FlightCity implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("city")
    private String city;

    @SerializedName("iata")
    private String iata;

    @SerializedName("airport")
    private String airport;


    public Response_‌FlightCity(int id, String city, String iata, String airport) {
        this.id = id;
        this.city = city;
        this.iata = iata;
        this.airport = airport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }
}