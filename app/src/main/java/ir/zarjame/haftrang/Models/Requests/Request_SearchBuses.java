package ir.zarjame.haftrang.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/30/17.
 */

public class Request_SearchBuses implements Serializable {

    @SerializedName("departure_code")
    private String departure_code;

    @SerializedName("arrival_code")
    private String arrival_code;

    @SerializedName("departure_name")
    private String departure_name;

    @SerializedName("arrival_name")
    private String arrival_name;

    @SerializedName("departure_date")
    private String departure_date;

    public Request_SearchBuses(String departure_code, String arrival_code, String departure_name, String arrival_name, String departure_date) {
        this.departure_code = departure_code;
        this.arrival_code = arrival_code;
        this.departure_name = departure_name;
        this.arrival_name = arrival_name;
        this.departure_date = departure_date;
    }

    public String getDeparture_code() {
        return departure_code;
    }

    public void setDeparture_code(String departure_code) {
        this.departure_code = departure_code;
    }

    public String getArrival_code() {
        return arrival_code;
    }

    public void setArrival_code(String arrival_code) {
        this.arrival_code = arrival_code;
    }

    public String getDeparture_name() {
        return departure_name;
    }

    public void setDeparture_name(String departure_name) {
        this.departure_name = departure_name;
    }

    public String getArrival_name() {
        return arrival_name;
    }

    public void setArrival_name(String arrival_name) {
        this.arrival_name = arrival_name;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }
}
