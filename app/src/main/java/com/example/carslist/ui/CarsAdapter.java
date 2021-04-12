package com.example.carslist.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.carslist.databinding.CellCarBinding;
import com.example.carslist.pojo.Cars;

public class CarsAdapter extends ListAdapter<Cars, CarsViewHolder> {

    protected CarsAdapter() {
        super(Cars.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CellCarBinding binding = CellCarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        Cars cars = getItem(position);
        holder.bindTo(cars);
    }
}
