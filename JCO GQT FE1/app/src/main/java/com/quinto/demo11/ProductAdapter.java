package com.quinto.demo11;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;





//extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context myContext;
    String[] productNames;

    String[] productPrices;
    TypedArray productImages;

    ClickListener clickListener;

    public ProductAdapter(Context context){
        myContext = context;
        this.productNames = myContext.getResources().getStringArray(R.array.products);
        this.productPrices = myContext.getResources().getStringArray(R.array.prices);
        this.productImages = myContext.getResources().obtainTypedArray(R.array.images);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.product_layout, parent, false);
//                View.inflate(myContext, R.layout.product_layout, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivProduct.setImageResource(productImages.getResourceId(position, -2));
//        holder.ivProduct.setImageResource(productImages.getResourceId(position, -1));
        holder.tvName.setText(productNames[position]);
        holder.tvPrice.setText("₱" + productPrices[position]);


//        ivProduct.setImageResouce(productImages.getResourceId(position, -1));
    }

    @Override
    public int getItemCount() {
        return productNames.length;
    }

    public void setItemClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProduct;
        TextView tvName;
        TextView tvPrice;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);



            ivProduct = itemView.findViewById(R.id.productImage);
            tvName = itemView.findViewById(R.id.productName);
            tvPrice = itemView.findViewById(R.id.productPrice);
            itemView.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
            if(clickListener != null){
                clickListener.OnClick(view, getAdapterPosition());
            }
        }

    }
}
