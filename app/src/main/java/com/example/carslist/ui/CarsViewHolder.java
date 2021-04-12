package com.example.carslist.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carslist.BR;
import com.example.carslist.R;
import com.example.carslist.databinding.CellCarBinding;
import com.example.carslist.pojo.Cars;
import com.example.carslist.util.ImageUtils;

public class CarsViewHolder extends RecyclerView.ViewHolder {

    CellCarBinding binding;

    public CarsViewHolder(@NonNull CellCarBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bindTo(Cars cars) {
        binding.setVariable(BR.car, cars);
        ImageUtils.loadImage(cars.getImageUrl(), binding.ivCar);
    }
}
