package com.example.devpro_btvn_day8;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> list;
    private Context context;


    RecyclerView bnvMain;

    public ProductAdapter(List<Product> list) {
        this.list = list;
        ;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context= parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.layout_items_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product =list.get(position);
        holder.tvTittle.setText(product.getTitle());
        holder.tvPrice.setText("$ "+product.getPrice().toString());
        holder.tvRating.setText(product.getRating().toString());
        Glide.with(context).load(product.getThumbnail()).into(holder.imgAnh);


        holder.llProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("product_id",product.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        BottomNavigationView bnvMain;
        LinearLayout llProduct;
        ImageView imgAnh;
        TextView tvTittle,tvPrice,tvRating;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAnh=itemView.findViewById(R.id.imgAnh);
            tvTittle=itemView.findViewById(R.id.tvTittle);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvRating=itemView.findViewById(R.id.tvRating);
            llProduct=itemView.findViewById(R.id.llProduct);

        }

    }
}
