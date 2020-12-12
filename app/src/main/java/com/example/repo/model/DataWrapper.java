package com.example.repo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWrapper {
    @SerializedName("data")
    private List<DataModel> mData;
    @SerializedName("error")
    private Boolean mError;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;

    public List<DataModel> getmData() {
        return mData;
    }

    public void setmData(List<DataModel> mData) {
        this.mData = mData;
    }

    public Boolean getmError() {
        return mError;
    }

    public void setmError(Boolean mError) {
        this.mError = mError;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}
