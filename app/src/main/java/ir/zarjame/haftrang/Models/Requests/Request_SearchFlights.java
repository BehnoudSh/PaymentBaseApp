package ir.zarjame.haftrang.Models.Requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/30/17.
 */

public class Request_SearchFlights implements Serializable {

    @SerializedName("departure")
    private String departure;

    @SerializedName("arrival")
    private String arrival;

    @SerializedName("departure_date")
    private String departure_date;

    public Request_SearchFlights(String departure, String arrival, String departure_date) {
        this.departure = departure;
        this.arrival = arrival;
        this.departure_date = departure_date;
    }


    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }
}
