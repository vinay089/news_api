package com.example.swordpc07.mywebview;

/**
 * Created by swordpc07 on 1/18/2017.
 */

public class LoadSourceNewsGetterSetter {

    String sortBy;
    String author;
    String title;
    String description;
    String url;
    String url_Image;


    public String getPublished_At() {
        return published_At;
    }

    public void setPublished_At(String published_At) {
        this.published_At = published_At;
    }

    public String getUrl_Image() {
        return url_Image;
    }

    public void setUrl_Image(String url_Image) {
        this.url_Image = url_Image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    String published_At;
}
