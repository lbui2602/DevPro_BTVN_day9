package com.example.devpro_btvn_day8.interfaces;

import com.example.devpro_btvn_day8.models.Product;

public interface ISavePresenter {
    void onSave(Product product);

    void unSave(Product product);
}
