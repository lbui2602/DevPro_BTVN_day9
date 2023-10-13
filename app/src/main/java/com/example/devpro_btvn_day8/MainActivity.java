package com.example.devpro_btvn_day8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.devpro_btvn_day8.models.DBHelper;
import com.example.devpro_btvn_day8.models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static List<Product> list;
    ProductService productService;
    BottomNavigationView bnvMain;
    NavHostFragment navHostFragment;
    NavController navController;
    static DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dbHelper=new DBHelper(this);
//        initData();
    }

    private void initData() {
        list=new ArrayList<Product>();
        productService=RetrofitClient.create(ProductService.class);
        productService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    if(response.code()==200){
                        ProductResponse productResponse=response.body();
                        list=productResponse.getProducts();
                        initView();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView() {
        bnvMain=findViewById(R.id.bnvMain);
        NavHostFragment navHostFragment= (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController=navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bnvMain,navController);
    }
}