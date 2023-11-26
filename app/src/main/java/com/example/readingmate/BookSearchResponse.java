package com.example.readingmate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookSearchResponse {
    @SerializedName("items")
    private List<BookItem> items;

    public List<BookItem> getItems() {
        return items;
    }
}

