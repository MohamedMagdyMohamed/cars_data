package com.example.carslist.pojo;

import java.util.List;

public interface StatusCallback<T> {

    void onSuccess(List<T> repos);

    void onError(String error);
}
