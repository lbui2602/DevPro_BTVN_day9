package com.example.devpro_btvn_day8;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("products?limit=0")
    Call<ProductResponse> getProducts();
    @GET("products/{idProduct}")
    Call<Product> getProductById(@Path("idProduct") String idProduct);

}
