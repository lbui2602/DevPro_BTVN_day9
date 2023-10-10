package com.example.devpro_btvn_day8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DongProductAdapter extends RecyclerView.Adapter<DongProductAdapter.DongProductViewHolder> {
    private Context context;
    private List<DongProduct> dongProductList;





    public void setData(List<DongProduct> list){
        this.dongProductList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DongProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_categories,parent,false);
        return new DongProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DongProductViewHolder holder, int position) {
        DongProduct dongProduct=dongProductList.get(position);
        if(dongProduct==null){
            return;
        }
        holder.tvName.setText(dongProduct.getNameTitle());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);

        ProductAdapter productAdapter =new ProductAdapter(dongProduct.getListProduct());
        holder.rcvProduct.setLayoutManager(linearLayoutManager);
        holder.rcvProduct.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        return dongProductList!=null?dongProductList.size():0;
    }

    public class DongProductViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rcvProduct;
        TextView tvName;

        public DongProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            rcvProduct=itemView.findViewById(R.id.rcvProduct);
        }
    }
}
