package com.example.ecommerceproject;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.ecommerceproject.services.UserService;
import com.google.android.material.navigation.NavigationView;

public class DrawerUtil {

    // Method to set up the navigation drawer
    public static void setupDrawer(final Activity activity, Toolbar toolbar,
                                   final DrawerLayout drawerLayout, NavigationView navigationView) {

        // Set a click listener on the toolbar navigation icon to open the drawer
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Set a navigation item click listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                // Close the drawer after selecting an item
                drawerLayout.closeDrawer(GravityCompat.START);
                // Handle different navigation items based on their IDs
                switch (id) {
                    case R.id.nav_home:
                        Toast.makeText(activity, "Home is clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_user:
                        Toast.makeText(activity, "Profile is clicked", Toast.LENGTH_SHORT).show();
                        Intent intentProfile = new Intent(activity, ProfileActivity.class);
                        activity.startActivity(intentProfile);
                        activity.finish();
                        break;

                    case R.id.nav_category:
                        Toast.makeText(activity, "Category is clicked", Toast.LENGTH_SHORT).show();
                        Intent intentCateogry = new Intent(activity, ProductListActivity.class);
                        activity.startActivity(intentCateogry);
                        activity.finish();
                        break;

                    case R.id.nav_logout:
                        Toast.makeText(activity, "Logout is clicked", Toast.LENGTH_SHORT).show();
                        UserService.logout(activity);
                        break;

                    case R.id.nav_trash:
                        Toast.makeText(activity, "List is clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, ProductActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });
    }
}

