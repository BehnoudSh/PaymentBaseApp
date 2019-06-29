package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

public class Response_Image_Item {

    @SerializedName("small")
    private String  regular;

    public Response_Image_Item(String regular) {
        this.regular = regular;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
