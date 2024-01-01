package com.example.ecommerceproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceproject.Adapters.ProductAdapter;
import com.example.ecommerceproject.R;
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

public class ProductFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Product> productList;
    private DatabaseReference reference;
    private ValueEventListener valueEventListener;
    private MaterialToolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerviewProduct);
     //   toolbar = view.findViewById(R.id.topAppbar);
      //  drawerLayout = view.findViewById(R.id.drawer_layout);
       // navigationView = view.findViewById(R.id.navigation_view);

        // Set up the navigation drawer
       // DrawerUtil.setupDrawer(requireActivity(), toolbar, drawerLayout, navigationView);

        // Set up the RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the product list and adapter
        productList = new ArrayList<>();
        ProductAdapter productAdapter = new ProductAdapter(requireContext(), productList);
        recyclerView.setAdapter(productAdapter);

        // Set up Firebase
        reference = FirebaseDatabase.getInstance().getReference("products");
        valueEventListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                productList.clear();
                for (com.google.firebase.database.DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Product product = itemSnapshot.getValue(Product.class);
                    productList.add(product);
                }
                productAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
