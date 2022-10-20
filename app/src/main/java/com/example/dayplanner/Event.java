package com.example.dayplanner;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String date;
    private String name;

    public Event(String date, String name) {
        this.date = date;
        this.name = name;
    }

    protected Event(Parcel in) {
        date = in.readString();
        name = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(name);
    }
}
