package com.hemantdave.newsapp;

/**
 * Created by INDIA on 6/28/2016.
 */
public class FeedArchitecturePojo {

    String webTitle;
    String webPublicationDate;
    String webUrl;
    String thumbnail;

    public FeedArchitecturePojo(String webTitle, String webPublicationDate, String webUrl, String thumbnail) {
        this.webTitle = webTitle;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
        this.thumbnail = thumbnail;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
