package com.example.carslist.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carslist.data.CarsListRepositoryImpl;
import com.example.carslist.pojo.Cars;
import com.example.carslist.pojo.StatusCallback;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final int ITEMS_PER_PAGE = 5;

    private final MutableLiveData<List<Cars>> _carsListLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> _errorLiveData = new MutableLiveData<>();

    public ArrayList<Cars> carsList = new ArrayList<>();

    public Boolean loading = false;
    public int pageNumber = 1;
    public Boolean allItemsLoaded = false;

    public LiveData<List<Cars>> carsListLiveData() {
        return _carsListLiveData;
    }

    public LiveData<String> errorLiveData() {
        return _errorLiveData;
    }

    public void fetchCarsList() {
        loading = true;
        CarsListRepositoryImpl carsListRepository = new CarsListRepositoryImpl();
        StatusCallback<Cars> statusCallback = new StatusCallback<Cars>() {
            @Override
            public void onSuccess(List<Cars> repos) {
                if (repos != null) {
                    loading = false;
                    pageNumber += 1;
                    carsList.addAll(repos);
                    if (repos.isEmpty() || repos.size() < ITEMS_PER_PAGE) {
                        allItemsLoaded = true;
                        _errorLiveData.postValue(null);
                    } else {
                        _carsListLiveData.postValue(repos);
                    }
                }
            }

            @Override
            public void onError(String error) {
                loading = false;
                allItemsLoaded = true;
                _errorLiveData.postValue(error);
            }
        };
        carsListRepository.fetchCarsList(pageNumber, statusCallback);
    }

    public void refresh() {
        pageNumber = 1;
        allItemsLoaded = false;
        loading = false;
        carsList.clear();
        fetchCarsList();
    }
}
