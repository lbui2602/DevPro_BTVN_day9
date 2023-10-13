package com.example.devpro_btvn_day8;

import com.example.devpro_btvn_day8.models.Product;

import java.util.List;

public class DongProduct {
    private String nameTitle;
    private List<Product> listProduct;

    public String getNameTitle() {
        return nameTitle;
    }

    public DongProduct() {
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public DongProduct(String nameTitle, List<Product> listProduct) {
        this.nameTitle = nameTitle;
        this.listProduct = listProduct;
    }
}
