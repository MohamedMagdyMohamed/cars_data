package com.example.carslist.data;

import com.example.carslist.pojo.Cars;
import com.example.carslist.pojo.StatusCallback;

public interface CarsListRepository {

    void fetchCarsList(int page, final StatusCallback<Cars> callbacks);
}
