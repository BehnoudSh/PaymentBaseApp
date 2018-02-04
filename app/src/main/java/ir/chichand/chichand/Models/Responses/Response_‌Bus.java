package ir.chichand.chichand.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_‌Bus implements Serializable {

    @SerializedName("ID")
    private Long ID;

    @SerializedName("BusType")
    private String BusType;

    @SerializedName("Price")
    private Long Price;

    @SerializedName("MidwayCity")
    private String MidwayCity;

    @SerializedName("MidwayCityCode")
    private String MidwayCityCode;

    @SerializedName("OriginTerminalPersianName")
    private String OriginTerminalPersianName;

    @SerializedName("DestinationTerminalPersianName")
    private String DestinationTerminalPersianName;

    @SerializedName("CompanyPersianName")
    private String CompanyPersianName;

    @SerializedName("DepartureTime")
    private String DepartureTime;

    @SerializedName("DepartureDate")
    private String DepartureDate;

    @SerializedName("AvailableSeatCount")
    private int AvailableSeatCount;

    @SerializedName("CompanyLogo")
    private String CompanyLogo;

    public Response_‌Bus(Long ID, String busType, Long price, String midwayCity, String midwayCityCode, String originTerminalPersianName, String destinationTerminalPersianName, String companyPersianName, String departureTime, String departureDate, int availableSeatCount, String companyLogo) {
        this.ID = ID;
        BusType = busType;
        Price = price;
        MidwayCity = midwayCity;
        MidwayCityCode = midwayCityCode;
        OriginTerminalPersianName = originTerminalPersianName;
        DestinationTerminalPersianName = destinationTerminalPersianName;
        CompanyPersianName = companyPersianName;
        DepartureTime = departureTime;
        DepartureDate = departureDate;
        AvailableSeatCount = availableSeatCount;
        CompanyLogo = companyLogo;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        BusType = busType;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public String getMidwayCity() {
        return MidwayCity;
    }

    public void setMidwayCity(String midwayCity) {
        MidwayCity = midwayCity;
    }

    public String getMidwayCityCode() {
        return MidwayCityCode;
    }

    public void setMidwayCityCode(String midwayCityCode) {
        MidwayCityCode = midwayCityCode;
    }

    public String getOriginTerminalPersianName() {
        return OriginTerminalPersianName;
    }

    public void setOriginTerminalPersianName(String originTerminalPersianName) {
        OriginTerminalPersianName = originTerminalPersianName;
    }

    public String getDestinationTerminalPersianName() {
        return DestinationTerminalPersianName;
    }

    public void setDestinationTerminalPersianName(String destinationTerminalPersianName) {
        DestinationTerminalPersianName = destinationTerminalPersianName;
    }

    public String getCompanyPersianName() {
        return CompanyPersianName;
    }

    public void setCompanyPersianName(String companyPersianName) {
        CompanyPersianName = companyPersianName;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public int getAvailableSeatCount() {
        return AvailableSeatCount;
    }

    public void setAvailableSeatCount(int availableSeatCount) {
        AvailableSeatCount = availableSeatCount;
    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }
}