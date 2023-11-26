package com.example.readingmate;

import com.google.gson.annotations.SerializedName;

public class BookItem {
    @SerializedName("title")
    private String title;

    @SerializedName("author")
    private String author;

    @SerializedName("image")
    private String imageURL;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("description")
    private String description;


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageURL() { return imageURL; }

    public String getPublisher() { return publisher; }

    public String getDescription() { return description; }
}
