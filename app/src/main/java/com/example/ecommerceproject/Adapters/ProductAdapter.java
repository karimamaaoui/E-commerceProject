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

    // Constructor to initialize the adapter with a context and a list of products
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    // Called when RecyclerView needs a new ViewHolder
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the item layout for the RecyclerView
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_item,parent,false);
        // Returning a new instance of ProductViewHolder with the inflated view
        return new ProductViewHolder(view);
    }

    // Called to bind data to a ViewHolder at a given position
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Setting the data to the views in the ViewHolder

        holder.recName.setText(productList.get(position).getName());
        holder.recPrice.setText(productList.get(position).getPrice());
        holder.recDesc.setText(productList.get(position).getDescription());
        holder.recCat.setText(productList.get(position).getCategory());
        // Loading product image using Glide library
        Glide.with(context)
                .load(productList.get(position).getImageUrl())
                .placeholder(R.drawable.akwhitelogo)
                .error(R.drawable.product_bg)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.recImage);

        // Setting an OnClickListener to handle item click
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating an intent to start the DetailsActivity

                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("name",productList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("price",productList.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("description",productList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("category",productList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("price",productList.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("imageUrl", productList.get(holder.getAdapterPosition()).getImageUrl());
                // Starting the DetailsActivity
                context.startActivity(intent);
            }
        });

    }
    // Returns the total number of items in the data set held by the adapter

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
