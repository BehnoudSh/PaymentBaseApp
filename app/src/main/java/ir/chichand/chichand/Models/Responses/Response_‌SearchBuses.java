package ir.chichand.chichand.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_‌SearchBuses implements Serializable {

    @SerializedName("Date")
    private String Date;

    @SerializedName("OriginPersianName")
    private String OriginPersianName;

    @SerializedName("DestinationPersianName")
    private String DestinationPersianName;

    @SerializedName("Items")
    private List<Response_‌Bus> Items;

    public Response_‌SearchBuses(String date, String originPersianName, String destinationPersianName, List<Response_‌Bus> items) {
        Date = date;
        OriginPersianName = originPersianName;
        DestinationPersianName = destinationPersianName;
        Items = items;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOriginPersianName() {
        return OriginPersianName;
    }

    public void setOriginPersianName(String originPersianName) {
        OriginPersianName = originPersianName;
    }

    public String getDestinationPersianName() {
        return DestinationPersianName;
    }

    public void setDestinationPersianName(String destinationPersianName) {
        DestinationPersianName = destinationPersianName;
    }

    public List<Response_‌Bus> getItems() {
        return Items;
    }

    public void setItems(List<Response_‌Bus> items) {
        Items = items;
    }
}


