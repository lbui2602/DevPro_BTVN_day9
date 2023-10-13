package com.example.devpro_btvn_day8.models;

import android.content.Context;

import com.example.devpro_btvn_day8.MainActivity;
import com.example.devpro_btvn_day8.RetrofitClient;
import com.example.devpro_btvn_day8.interfaces.ISavePresenter;

import java.util.List;

public class Database {
    ISavePresenter iSavePresenter;

    public Database(ISavePresenter iSavePresenter) {
        this.iSavePresenter = iSavePresenter;
    }

    public void clickSave(Product product){
        if(product.getCheck()==0){
            iSavePresenter.onSave(product);
        }else if (product.getCheck()==1){
            iSavePresenter.unSave(product);
        }
    }

}
