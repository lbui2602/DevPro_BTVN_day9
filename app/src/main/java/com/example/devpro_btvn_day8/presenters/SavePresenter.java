package com.example.devpro_btvn_day8.presenters;

import android.content.Context;

import com.example.devpro_btvn_day8.interfaces.ISavePresenter;
import com.example.devpro_btvn_day8.interfaces.ISaveView;
import com.example.devpro_btvn_day8.models.DBHelper;
import com.example.devpro_btvn_day8.models.Database;
import com.example.devpro_btvn_day8.models.Product;

public class SavePresenter implements ISavePresenter {
    private ISaveView iSaveView;
    private Database database;

    public SavePresenter(ISaveView iSaveView) {
        this.iSaveView = iSaveView;
        database=new Database(this);
    }

    public void clickSave(Product product){
        database.clickSave(product);
    }

    @Override
    public void onSave(Product product) {
        iSaveView.onSave(product);
    }

    @Override
    public void unSave(Product product) {
        iSaveView.unSave(product);
    }
}
