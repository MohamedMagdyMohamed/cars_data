package com.example.carslist.pojo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cars {

    public static final DiffUtil.ItemCallback<Cars> DIFF_CALLBACK = new DiffUtil.ItemCallback<Cars>() {

        @Override
        public boolean areItemsTheSame(@NonNull Cars oldItem, @NonNull Cars newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Cars oldItem, @NonNull Cars newItem) {
            return oldItem.id.equals(newItem.id) &&
                    oldItem.brand.equals(newItem.brand) &&
                    oldItem.isUsed.equals(newItem.isUsed);
        }
    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("constractionYear")
    @Expose
    private String constractionYear;
    @SerializedName("isUsed")
    @Expose
    private Boolean isUsed;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        this.constractionYear = constractionYear;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public String getImageUrl() {
        if (imageUrl != null) {
            return imageUrl;
        } else {
            return "";
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
