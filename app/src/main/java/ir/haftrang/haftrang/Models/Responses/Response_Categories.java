package ir.haftrang.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Categories implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("cat_id")
    private String cat_id;

    @SerializedName("cat_level")
    private String cat_level;

    @SerializedName("persian_title")
    private String persian_title;

    @SerializedName("cat_icon")
    private String cat_icon;

    @SerializedName("isenabled")
    private String isenabled;

    @SerializedName("minversion")
    private String minversion;


    @SerializedName("visibility")
    private String visibility;


    private int bg_color;


    public Response_Categories(String id, String cat_id, String cat_level, String persian_title, String cat_icon, String isenabled, String minversion, String visibility, int bg_color) {
        this.id = id;
        this.cat_id = cat_id;
        this.cat_level = cat_level;
        this.persian_title = persian_title;
        this.cat_icon = cat_icon;
        this.isenabled = isenabled;
        this.minversion = minversion;
        this.visibility = visibility;
        this.bg_color = bg_color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_level() {
        return cat_level;
    }

    public void setCat_level(String cat_level) {
        this.cat_level = cat_level;
    }

    public String getPersian_title() {
        return persian_title;
    }

    public void setPersian_title(String persian_title) {
        this.persian_title = persian_title;
    }

    public String getCat_icon() {
        return cat_icon;
    }

    public void setCat_icon(String cat_icon) {
        this.cat_icon = cat_icon;
    }

    public String getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(String isenabled) {
        this.isenabled = isenabled;
    }

    public int getBg_color() {
        return bg_color;
    }

    public void setBg_color(int bg_color) {
        this.bg_color = bg_color;
    }

    public String getMinversion() {
        return minversion;
    }

    public void setMinversion(String minversion) {
        this.minversion = minversion;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
