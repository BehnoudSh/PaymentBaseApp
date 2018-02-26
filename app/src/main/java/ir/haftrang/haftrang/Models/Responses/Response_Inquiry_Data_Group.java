package ir.haftrang.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinabehnoud on 12/29/17.
 */

public class Response_Inquiry_Data_Group implements Serializable {

    @SerializedName("group_title")
    private String group_title;

    @SerializedName("group")
    private int group;

    @SerializedName("group_currency")
    private String group_currency;

    public Response_Inquiry_Data_Group(String group_title, int group, String group_currency) {
        this.group_title = group_title;
        this.group = group;
        this.group_currency = group_currency;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getGroup_currency() {
        return group_currency;
    }

    public void setGroup_currency(String group_currency) {
        this.group_currency = group_currency;
    }
}
