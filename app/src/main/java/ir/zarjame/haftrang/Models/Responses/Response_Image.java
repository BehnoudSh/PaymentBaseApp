package ir.zarjame.haftrang.Models.Responses;

import com.google.gson.annotations.SerializedName;

public class Response_Image {

    @SerializedName("urls")
    private Response_Image_Item  urls;

    public Response_Image(Response_Image_Item urls) {
        this.urls = urls;
    }

    public Response_Image_Item getUrls() {
        return urls;
    }

    public void setUrls(Response_Image_Item urls) {
        this.urls = urls;
    }
}
