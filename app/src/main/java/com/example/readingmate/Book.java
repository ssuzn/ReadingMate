package com.example.readingmate;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;

    private String author;

    private String imageURL;

    private String publisher;

    private String description;

    public String id;

    private boolean isBookmarked;

    public Book(String title, String author, String imageURL, String publisher, String description) {
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.publisher = publisher;
        this.description = description;
    }

    public String getTitle() { return title; }

    public String getAuthor() {return author; }

    public String getImageURL() { return imageURL; }

    public String getPublisher() { return publisher; }

    public String getDescription() { return description; }

    public String getId() { return id; }

    public boolean isBookmarked() { return isBookmarked; }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return id.equals(book.id);
    }

    // Parcelable 인터페이스 구현 부분
    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        imageURL = in.readString();
        publisher = in.readString();
        description = in.readString();
        id = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}