package com.example.ecommerceproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecommerceproject.services.UserService;

public class CartFragment extends Fragment {

    Button logoutbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        // Initialize the button
        logoutbtn = view.findViewById(R.id.logoutbtn);

        // Set OnClickListener for the button
        // Set OnClickListener for the button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the logout method from your UserService
                UserService.logout(v.getContext());
            }
        });

        return view;
    }
}