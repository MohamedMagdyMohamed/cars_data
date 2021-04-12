package com.example.carslist.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.carslist.pojo.Cars;
import com.example.carslist.pojo.CarsListResponse;
import com.example.carslist.pojo.StatusCallback;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    public static void fetchCarsList(int page, final StatusCallback<Cars> callbacks) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        CarsApiService carsApiService = retrofitClient.getApi();
        carsApiService.fetchCarsList(page).enqueue(
                new Callback<CarsListResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CarsListResponse> call, @NonNull Response<CarsListResponse> response) {
                        Log.d("com.test", String.format("Got a response: %s", response));
                        if (response.isSuccessful()) {
                            List<Cars> carsList;
                            if (response.body() != null) {
                                carsList = response.body().getCars();
                            } else {
                                carsList = Collections.emptyList();
                            }
                            callbacks.onSuccess(carsList);
                        } else {
                            try {
                                String body = response.errorBody().string();
                                callbacks.onError(body != null ? body : "Unknown error");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CarsListResponse> call, @NonNull Throwable t) {
                        Log.d("com.test", "Failed to get data");
                        callbacks.onError(t.getMessage() != null ? t.getMessage() : "Unknown error");
                    }
                }
        );
    }
}
