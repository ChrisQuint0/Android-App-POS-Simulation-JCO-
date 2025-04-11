package com.quinto.demo11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int position;
    ImageView ivDetail;
    TextView tvDetailName;
    TextView tvDetailPrice;
    TextView tvDescription;
    EditText etDetailQuantity;


    Button btnAdd;

    Button btnCart;

    ArrayList<String> cartNames;
    ArrayList<String> cartPrices;
    ArrayList<String> cartQuantity;
    ArrayList<String> cartSubTotal;

    DecimalFormat decimalFormat;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        // Inflate the layout for this fragment\


        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        ivDetail = root.findViewById(R.id.detailImage);
        tvDetailName = root.findViewById(R.id.detailName);
        tvDetailPrice = root.findViewById(R.id.detailPrice);
        tvDescription = root.findViewById(R.id.description);
        etDetailQuantity = root.findViewById(R.id.editTextQuantity);

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

        position = getArguments().getInt("position", 0);
        ivDetail.setImageDrawable(root.getResources().obtainTypedArray(R.array.images).getDrawable(position));
        tvDetailName.setText(root.getResources().getStringArray(R.array.products)[position]);
        tvDetailPrice.setText("₱" + root.getResources().getStringArray(R.array.prices)[position]);

        switch(position){
            case 0:
                tvDescription.setText(R.string.alcapone_desc);
                break;
            case 1:
                tvDescription.setText(R.string.avocado_desc);
                break;
            case 2:
                tvDescription.setText(R.string.berryspears_desc);
                break;
            case 3:
                tvDescription.setText(R.string.blackjack_desc);
                break;
            case 4:
                tvDescription.setText(R.string.caviarstrawberry_desc);
                break;
            case 5:
                tvDescription.setText(R.string.cheesecake_desc);
                break;
            case 6:
                tvDescription.setText(R.string.choconutzy);
                break;
            case 7:
                tvDescription.setText(R.string.jpops);
                break;
        }

        btnAdd = root.findViewById(R.id.btnAddToCart);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(etDetailQuantity.length() == 0 || Integer.parseInt(etDetailQuantity.getEditableText().toString()) == 0){
                    Snackbar.make(view, "Invalid Quantity", Snackbar.LENGTH_LONG).show();
                }else{
                    cartNames.add(root.getResources().getStringArray(R.array.products)[position]);
                    cartPrices.add(root.getResources().getStringArray(R.array.prices)[position]);
                    cartQuantity.add(etDetailQuantity.getEditableText().toString());
                    decimalFormat = new DecimalFormat("#,###.00");
                    double amount = Double.parseDouble(etDetailQuantity.getEditableText().toString()) * Double.parseDouble(root.getResources().getStringArray(R.array.prices)[position]);
                    cartSubTotal.add(decimalFormat.format(amount));
                    Bundle bundle2 = new Bundle();
                    bundle2.putStringArrayList("cartNames", cartNames);
                    bundle2.putStringArrayList("cartPrices", cartPrices);
                    bundle2.putStringArrayList("cartQuantity", cartQuantity);
                    bundle2.putStringArrayList("cartSubTotal", cartSubTotal);
                    Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_cartFragment, bundle2);


                    //cartNames.add(root.getResources().getStringArray(R.array.products)[position]);
                }
            }
        });

        btnCart = root.findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("cartNames", cartNames);
                bundle.putStringArrayList("cartPrices", cartPrices);
                bundle.putStringArrayList("cartQuantity", cartQuantity);
                bundle.putStringArrayList("cartSubTotal", cartSubTotal);
                Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_cartFragment, bundle);
            }
        });
        return root;
    }
}