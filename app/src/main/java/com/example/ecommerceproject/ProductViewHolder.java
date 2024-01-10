package com.example.ecommerceproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder{

   public TextView recName,recPrice,recDesc,recCat;
   public ImageView recImage;
   public CardView recCard;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        // Initialize TextViews, ImageView, and CardView by finding their corresponding views in the itemView
        recName=itemView.findViewById(R.id.recName);
        recPrice=itemView.findViewById(R.id.recPrice);
        recDesc=itemView.findViewById(R.id.recDesc);
        recCat=itemView.findViewById(R.id.recCat);
        recImage=itemView.findViewById(R.id.recImage);
        recCard=itemView.findViewById(R.id.recCard);
    }
}
