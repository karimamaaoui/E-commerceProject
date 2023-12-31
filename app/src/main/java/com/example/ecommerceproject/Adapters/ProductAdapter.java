package com.example.ecommerceproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.ecommerceproject.DetailsActivity;
import com.example.ecommerceproject.ProductViewHolder;
import com.example.ecommerceproject.R;
import com.example.ecommerceproject.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_item,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.recName.setText(productList.get(position).getName());
        holder.recPrice.setText(productList.get(position).getPrice());
        holder.recDesc.setText(productList.get(position).getDescription());
        holder.recCat.setText(productList.get(position).getCategory());

        Glide.with(context)
                .load(productList.get(position).getImageUrl())
                .placeholder(R.drawable.akwhitelogo)
                .error(R.drawable.product_bg)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.recImage);


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("name",productList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("price",productList.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("description",productList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("category",productList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("price",productList.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("imageUrl", productList.get(holder.getAdapterPosition()).getImageUrl());


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
