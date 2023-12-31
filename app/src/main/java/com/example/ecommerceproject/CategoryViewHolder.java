package com.example.ecommerceproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder{
    public TextView recNameCategory;
    public ImageView recImageView;
    public CardView recCard;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        recNameCategory=itemView.findViewById(R.id.recName);
        recImageView=itemView.findViewById(R.id.recImage);
        recCard=itemView.findViewById(R.id.recCard);
    }
}
