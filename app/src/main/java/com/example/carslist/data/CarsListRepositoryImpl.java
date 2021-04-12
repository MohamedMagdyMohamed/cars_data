package com.example.carslist.data;

import com.example.carslist.api.ApiManager;
import com.example.carslist.pojo.Cars;
import com.example.carslist.pojo.StatusCallback;

public class CarsListRepositoryImpl implements CarsListRepository {

    @Override
    public void fetchCarsList(int page, final StatusCallback<Cars> callbacks) {
        ApiManager.fetchCarsList(page, callbacks);
    }
}
