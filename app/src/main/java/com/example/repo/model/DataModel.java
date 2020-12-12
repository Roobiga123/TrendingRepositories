package com.example.repo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "datatable")
public class DataModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String Title;
    @SerializedName("desc")
    @ColumnInfo(name = "desc")
    private String Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("lang")
    @ColumnInfo(name = "lang")
    private  String Language;
    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    private  String Rating;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Language='" + Language + '\'' +
                ", Rating='" + Rating + '\'' +
                '}';
    }
}
