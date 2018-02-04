package ir.chichand.chichand.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_‌Flight implements Serializable {

    @SerializedName("departure")
    private String departure;

    @SerializedName("arrival")
    private String arrival;

    @SerializedName("departure_date")
    private String departure_date;

    @SerializedName("departure_time")
    private String departure_time;

    @SerializedName("arrival_date")
    private String arrival_date;

    @SerializedName("arrival_time")
    private String arrival_time;

    @SerializedName("airline")
    private String airline;

    @SerializedName("flight_class")
    private String flight_class;

    @SerializedName("flight_type")
    private String flight_type;

    @SerializedName("price")
    private String price;

    @SerializedName("price_currency")
    private String price_currency;

    @SerializedName("reference")
    private String reference;

    @SerializedName("reference_url")
    private String reference_url;

    public Response_‌Flight(String departure, String arrival, String departure_date, String departure_time, String arrival_date, String arrival_time, String airline, String flight_class, String flight_type, String price, String price_currency, String reference, String reference_url) {
        this.departure = departure;
        this.arrival = arrival;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.airline = airline;
        this.flight_class = flight_class;
        this.flight_type = flight_type;
        this.price = price;
        this.price_currency = price_currency;
        this.reference = reference;
        this.reference_url = reference_url;
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

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight_class() {
        return flight_class;
    }

    public void setFlight_class(String flight_class) {
        this.flight_class = flight_class;
    }

    public String getFlight_type() {
        return flight_type;
    }

    public void setFlight_type(String flight_type) {
        this.flight_type = flight_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference_url() {
        return reference_url;
    }

    public void setReference_url(String reference_url) {
        this.reference_url = reference_url;
    }
}