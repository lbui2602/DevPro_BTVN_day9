package com.example.devpro_btvn_day8.uis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devpro_btvn_day8.DongProduct;
import com.example.devpro_btvn_day8.adapters.DongProductAdapter;
import com.example.devpro_btvn_day8.IClickListener;
import com.example.devpro_btvn_day8.IClickSave;
import com.example.devpro_btvn_day8.ProductResponse;
import com.example.devpro_btvn_day8.ProductService;
import com.example.devpro_btvn_day8.R;
import com.example.devpro_btvn_day8.RetrofitClient;
import com.example.devpro_btvn_day8.interfaces.ISaveView;
import com.example.devpro_btvn_day8.models.DBHelper;
import com.example.devpro_btvn_day8.models.Product;
import com.example.devpro_btvn_day8.presenters.SavePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IClickListener,IClickSave, ISaveView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rcvHome;
    private DongProductAdapter dongProductAdapter;
    private List<DongProduct> listDongProduct;
    private SavePresenter savePresenter;
    List<Product> listAllProduct;

    static DBHelper dbHelper;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvHome=view.findViewById(R.id.rcvHome);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        rcvHome.setLayoutManager(linearLayoutManager);
        dongProductAdapter=new DongProductAdapter(HomeFragment.this,HomeFragment.this);
        rcvHome.setAdapter(dongProductAdapter);
        getData();
//        getActivity().deleteDatabase("product.db");
        dbHelper=new DBHelper(getActivity());
    }

    private void getData() {
        listDongProduct=new ArrayList<>();

        RetrofitClient.create(ProductService.class).getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    if(response.code()==200){
                        ProductResponse productResponse=response.body();
                        listAllProduct=productResponse.getProducts();
                        resetData();
                        List<Product> listProductHotDeal=listAllProduct.stream()
                                .filter(product -> product.getRating() >4.9)
                                .collect(Collectors.toList());
                        DongProduct dongProduct=new DongProduct("Hot Deals",listProductHotDeal);
                        listDongProduct.add(dongProduct);

                        List<Product> listProductPopular=listAllProduct.stream()
                                .filter(product -> product.getStock() <100)
                                .collect(Collectors.toList());
                        DongProduct dongProduct1=new DongProduct("Most Poplular",listProductPopular);
                        listDongProduct.add(dongProduct1);

                        List<Product> listProductiPhone=listAllProduct.stream()
                                .filter(product -> product.getCategory().equals("smartphones") && product.getBrand().equals("Apple"))
                                .collect(Collectors.toList());
                        DongProduct dongProduct2=new DongProduct("Iphones",listProductiPhone);
                        listDongProduct.add(dongProduct2);

                        List<Product> listProductiPad=listAllProduct.stream()
                                .filter(product -> product.getBrand().equals("Apple"))
                                .collect(Collectors.toList());
                        DongProduct dongProduct3=new DongProduct("Ipads",listProductiPad);
                        listDongProduct.add(dongProduct3);

                        List<Product> listProductMac=listAllProduct.stream()
                                .filter(product -> product.getCategory().equals("laptops"))
                                .collect(Collectors.toList());
                        DongProduct dongProduct4=new DongProduct("macs",listProductMac);
                        listDongProduct.add(dongProduct4);

                        dongProductAdapter.setData(listDongProduct);

                    }
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }
    public void resetData(){
        int dem;
        for(int i=0;i<listAllProduct.size();i++){
            dem=0;
            for(int j=0;j<dbHelper.getProducts().size();j++){
                if(listAllProduct.get(i).getId()==dbHelper.getProducts().get(j).getId()){
                    listAllProduct.get(i).setCheck(1);
                    dongProductAdapter.notifyDataSetChanged();
                    dem=1;
                }
            }
            if(dem==0){
                listAllProduct.get(i).setCheck(0);
                dongProductAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onItemClick(int productID) {
        Bundle bundle = new Bundle();
        bundle.putString("product_id", String.valueOf(productID));

        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
        navController.navigate(R.id.action_homeFragment_to_productDetailsFragment, bundle);
    }


    @Override
    public void onSaveClick(Product product) {
        savePresenter=new SavePresenter(this);
        savePresenter.clickSave(product);
    }

    @Override
    public void onSave(Product product) {
        dbHelper.addProduct(product);
        resetData();
    }

    @Override
    public void unSave(Product product) {
        dbHelper.deleteProduct(product.getId());
        resetData();
    }
    public static List<Product> getProductWish(){
       List<Product> list=new ArrayList<>();
       list=dbHelper.getProducts();
       return list;
    }
}