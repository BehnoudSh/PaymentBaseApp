package ir.haftrang.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Config implements Serializable {

    @SerializedName("enabled")
    private int enabled;

    @SerializedName("version_code")
    private int version_code;

    @SerializedName("forced_update")
    private int forced_update;

    @SerializedName("update_url")
    private String update_url;

    @SerializedName("quote")
    private String quote;

    public Response_Config(int enabled, int version_code, int forced_update, String update_url, String quote) {
        this.enabled = enabled;
        this.version_code = version_code;
        this.forced_update = forced_update;
        this.update_url = update_url;
        this.quote = quote;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public int getForced_update() {
        return forced_update;
    }

    public void setForced_update(int forced_update) {
        this.forced_update = forced_update;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
