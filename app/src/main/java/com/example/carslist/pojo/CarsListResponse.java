package com.example.carslist.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsListResponse {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("data")
    @Expose
    private final List<Cars> cars = null;

    @SerializedName("erroe")
    @Expose
    private final Error erroe = null;

    public int getStatus() {
        return status;
    }

    public List<Cars> getCars() {
        return cars;
    }

    public Error getErroe() {
        return erroe;
    }
}
