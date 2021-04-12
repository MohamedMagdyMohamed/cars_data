package com.example.carslist.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.carslist.R;

public class ImageUtils {

    public static void loadImage(String imgUrl, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .circleCrop()
                .into(imageView);
    }
}
