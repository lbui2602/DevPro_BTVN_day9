package com.example.devpro_btvn_day8.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.devpro_btvn_day8.IClickListener;
import com.example.devpro_btvn_day8.IClickSave;
import com.example.devpro_btvn_day8.R;
import com.example.devpro_btvn_day8.models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> list;
    private Context context;


    private IClickListener iClickListener;
    private IClickSave iClickSave;
    public ProductAdapter(List<Product> list) {
        this.list = list;

    }

    public ProductAdapter(List<Product> list ,IClickListener iClickListener,IClickSave iClickSave) {
        this.list = list;
        this.iClickListener=iClickListener;
        this.iClickSave=iClickSave;
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
                iClickListener.onItemClick(product.getId());
            }
        });
        if(product.getCheck()==1){
            holder.imgSave.setImageResource(R.drawable.save_true);
        }else if(product.getCheck()==0){
            holder.imgSave.setImageResource(R.drawable.save);
        }
        holder.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickSave.onSaveClick(product);
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
        ImageView imgAnh,imgSave;
        TextView tvTittle,tvPrice,tvRating;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            bnvMain=itemView.findViewById(R.id.bnvMain);
            imgAnh=itemView.findViewById(R.id.imgAnh);
            tvTittle=itemView.findViewById(R.id.tvTittle);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvRating=itemView.findViewById(R.id.tvRating);
            llProduct=itemView.findViewById(R.id.llProduct);
            imgSave=itemView.findViewById(R.id.imgSave);
        }

    }
}
