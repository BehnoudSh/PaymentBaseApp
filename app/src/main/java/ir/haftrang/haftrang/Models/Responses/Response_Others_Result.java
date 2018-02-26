package ir.haftrang.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Others_Result implements Serializable {

    @SerializedName("is_accessible")
    private boolean is_accessible;

    @SerializedName("price")
    private int price;

    @SerializedName("shop_id")
    private int shop_id;

    @SerializedName("slug_name")
    private String slug_name;

    @SerializedName("english_name")
    private String english_name;

    @SerializedName("shop_name")
    private String shop_name;

    @SerializedName("availability")
    private boolean availability;

    @SerializedName("torob_category")
    private int torob_category;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("persian_name")
    private String persian_name;

//             "web_client_absolute_url": "/p/9cb755e5-d833-41ef-aae1-3e35d8908393/%DB%8C%D8%AE%DA%86%D8%A7%D9%84-%D8%B3%D8%A7%DB%8C%D8%AF-%D8%A8%D8%A7%DB%8C-%D8%B3%D8%A7%DB%8C%D8%AF-%D9%85%D8%AF%D9%84samsung-rs51/",
//             "discount_info": [],

//             "shop_num": 7,
//             "random_key": "9cb755e5-d833-41ef-aae1-3e35d8908393",
//             "attributes": {},
//            "more_info_url": "http://api.torob.com/search-result-base-product-more-info-with-log/?search_id=112951004&prk=9cb755e5-d833-41ef-aae1-3e35d8908393&rank=0&source=next"


    public Response_Others_Result(boolean is_accessible, int price, int shop_id, String slug_name, String english_name, String shop_name, boolean availability, int torob_category, String image_url, String persian_name) {
        this.is_accessible = is_accessible;
        this.price = price;
        this.shop_id = shop_id;
        this.slug_name = slug_name;
        this.english_name = english_name;
        this.shop_name = shop_name;
        this.availability = availability;
        this.torob_category = torob_category;
        this.image_url = image_url;
        this.persian_name = persian_name;
    }

    public boolean is_accessible() {
        return is_accessible;
    }

    public void setIs_accessible(boolean is_accessible) {
        this.is_accessible = is_accessible;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getSlug_name() {
        return slug_name;
    }

    public void setSlug_name(String slug_name) {
        this.slug_name = slug_name;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getTorob_category() {
        return torob_category;
    }

    public void setTorob_category(int torob_category) {
        this.torob_category = torob_category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPersian_name() {
        return persian_name;
    }

    public void setPersian_name(String persian_name) {
        this.persian_name = persian_name;
    }
}
