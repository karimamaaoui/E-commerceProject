package com.example.ecommerceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.ecommerceproject.fragments.CartFragment;
import com.example.ecommerceproject.fragments.HomeFragment;
import com.example.ecommerceproject.fragments.ProductFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Initializing views
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Setting background of BottomAppBar to null for a transparent look
        bottomAppBar.setBackground(null);
        // Setting up item selection listener for BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.menu_profile:
                    selectedFragment = new ProductFragment();
                    break;
                case R.id.menu_hello:
                    selectedFragment = new CartFragment();
                    break;
            }
            // Replacing the current fragment with the selected one
            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
            }

            return true;
        });
    }
    // Method to replace the current fragment with a new one

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
