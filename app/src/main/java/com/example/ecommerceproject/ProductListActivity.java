package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;

import com.example.ecommerceproject.Adapters.ProductAdapter;
import com.example.ecommerceproject.models.Product;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> productList;
    DatabaseReference reference;
    ValueEventListener valueEventListener;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        toolbar = findViewById(R.id.topAppbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);

        // Set up the navigation drawer
        DrawerUtil.setupDrawer(this, toolbar, drawerLayout, navigationView);

        try{
        recyclerView=findViewById(R.id.recyclerviewProduct);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductListActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        productList=new ArrayList<>();

        ProductAdapter productAdapter=new ProductAdapter(ProductListActivity.this,productList);
        recyclerView.setAdapter(productAdapter);

        reference= FirebaseDatabase.getInstance().getReference("products");
        valueEventListener=reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
                    Product product=itemSnapshot.getValue(Product.class);
                    productList.add(product);
                }


                productAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ProductListActivity", "DatabaseError: " + error.getMessage());
            }
        });
    } catch (Exception e) {
        Log.e("ProductListActivity", "Error in onCreate: " + e.getMessage());
        e.printStackTrace();
    }
    }
}