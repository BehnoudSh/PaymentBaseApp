package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bSherafati on 2/5/2018.
 */

public class Response_SearchFlights implements Serializable {

    @SerializedName("available_flight")
    private List<Response_Flight> available_flight;

    public Response_SearchFlights(List<Response_Flight> available_flight) {
        this.available_flight = available_flight;
    }

    public List<Response_Flight> getAvailable_flight() {
        return available_flight;
    }

    public void setAvailable_flight(List<Response_Flight> available_flight) {
        this.available_flight = available_flight;
    }
}
