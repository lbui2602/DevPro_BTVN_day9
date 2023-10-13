package com.example.devpro_btvn_day8.uis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devpro_btvn_day8.DongProduct;
import com.example.devpro_btvn_day8.R;
import com.example.devpro_btvn_day8.adapters.DongProductAdapter;
import com.example.devpro_btvn_day8.adapters.ProductAdapter;
import com.example.devpro_btvn_day8.models.Product;
import com.example.devpro_btvn_day8.presenters.SavePresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rcvWish;
    private ProductAdapter productAdapter;
    List<Product> list;

    public WishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishFragment newInstance(String param1, String param2) {
        WishFragment fragment = new WishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvWish=view.findViewById(R.id.rcvWish);
        list=HomeFragment.getProductWish();
        for (Product x:list
             ) {
            x.setCheck(1);
        }
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this.getContext(),2);
        rcvWish.setLayoutManager(gridLayoutManager);
        productAdapter=new ProductAdapter(list);
        rcvWish.setAdapter(productAdapter);
    }
}