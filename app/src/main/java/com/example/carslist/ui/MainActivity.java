package com.example.carslist.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.carslist.databinding.ActivityMainBinding;
import com.paginate.Paginate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Paginate.Callbacks {

    private ActivityMainBinding binding = null;
    private CarsAdapter carsAdapter = null;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            viewModel.fetchCarsList();
        }

        setupSwipeForRefresh();
        setupAdapter();
        setupRecycleView();
        setupPaginate();
        observeData();
    }

    private void setupSwipeForRefresh() {
        binding.swipeRefresh.setOnRefreshListener(() -> {
            binding.swipeRefresh.setRefreshing(false);
            viewModel.refresh();
            carsAdapter.submitList(null);
        });
    }

    private void setupAdapter() {
        carsAdapter = new CarsAdapter();
        carsAdapter.submitList(new ArrayList(viewModel.carsList));
    }

    private void setupRecycleView() {
        binding.rvCars.setAdapter(carsAdapter);
        binding.rvCars.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCars.setHasFixedSize(true);
    }

    private void setupPaginate() {
        Paginate paginate = Paginate.with(binding.rvCars, this)
                .setLoadingTriggerThreshold(2)
                .build();
        paginate.setHasMoreDataToLoad(!viewModel.allItemsLoaded);
    }

    private void observeData() {
        viewModel.carsListLiveData().observe(this, cars -> {
            carsAdapter.submitList(new ArrayList<>(viewModel.carsList));
        });
        viewModel.errorLiveData().observe(this, error -> {
            if (error != null) {
                showToast(error);
            }
            carsAdapter.notifyDataSetChanged();
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        if (viewModel.pageNumber == 1) return;
        viewModel.fetchCarsList();
    }

    @Override
    public boolean isLoading() {
        return viewModel.loading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return viewModel.allItemsLoaded;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        carsAdapter = null;
    }
}