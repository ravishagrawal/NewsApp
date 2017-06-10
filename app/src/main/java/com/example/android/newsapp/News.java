package com.example.android.newsapp;

/**
 * Created by hp on 07-06-2017.
 */

public class News {

    String mTitle;
    String mAuthor;
    String mUrl;
    String mDate;
    String mSection;

    public News(String title, String author, String url, String date, String section) {
        mTitle = title;
        mAuthor = author;
        mUrl = url;
        mDate = date;
        mSection = section;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getAuthor() {
        return mAuthor;
    }


    public String getDate() {
        return mDate;
    }


    public String getSection() {
        return mSection;
    }


    @Override
    public String toString() {
        return "News{" +
                "title='" + mTitle + '\'' +
                ", author='" + mAuthor + '\'' +
                ", url='" + mUrl + '\'' +
                ", date='" + mDate + '\'' +
                ", section='" + mSection + '\'' +
                '}';
    }
}
