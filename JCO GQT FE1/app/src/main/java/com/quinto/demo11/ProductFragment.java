package com.quinto.demo11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView rvProduct;
    ProductAdapter productAdapter;

    Button buttonCart;

    ArrayList<String> cartNames;
    ArrayList<String> cartPrices;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;




    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        if(getArguments() == null){
            cartNames = new ArrayList<String>();
            cartPrices = new ArrayList<String>();
            cartQuantity = new ArrayList<String>();
            cartSubTotal = new ArrayList<String>();
        } else{
            cartNames = getArguments().getStringArrayList("cartNames");
            cartPrices = getArguments().getStringArrayList("cartPrices");
            cartQuantity = getArguments().getStringArrayList("cartQuantity");
            cartSubTotal = getArguments().getStringArrayList("cartSubTotal");


        }

        productAdapter = new ProductAdapter(getContext());
        productAdapter.setItemClickListener(new ClickListener(){
            @Override
            public void OnClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putStringArrayList("cartNames", cartNames);
                bundle.putStringArrayList("cartPrices", cartPrices);
                bundle.putStringArrayList("cartQuantity", cartQuantity);
                bundle.putStringArrayList("cartSubTotal", cartSubTotal);
                Navigation.findNavController(view).navigate(R.id.action_productFragment_to_detailFragment, bundle);

            }
        });




        rvProduct = root.findViewById(R.id.productRecyclerView);
        rvProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProduct.setAdapter(productAdapter);

        buttonCart = root.findViewById(R.id.btnCart);

        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("cartNames", cartNames);
                bundle.putStringArrayList("cartPrices", cartPrices);
                bundle.putStringArrayList("cartQuantity", cartQuantity);
                bundle.putStringArrayList("cartSubTotal", cartSubTotal);
                Navigation.findNavController(view).navigate(R.id.action_productFragment_to_cartFragment, bundle);
            }
        });



        return root;
    }

    public void GoCart(View view){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("cartNames", cartNames);
        bundle.putStringArrayList("cartPrices", cartPrices);
        bundle.putStringArrayList("cartQuantity", cartQuantity);
        bundle.putStringArrayList("cartSubTotal", cartSubTotal);
        Navigation.findNavController(view).navigate(R.id.action_productFragment_to_cartFragment, bundle);
    }


}

