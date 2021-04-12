package com.example.carslist.api;

import com.example.carslist.pojo.CarsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarsApiService {

    @GET("cars")
    Call<CarsListResponse> fetchCarsList(@Query("page") int page);
}
