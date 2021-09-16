package com.example.teambeta;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.regex.Pattern;

import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        textInputEmail = v.findViewById(R.id.login_email);
        textInputPassword = v.findViewById(R.id.login_password);
        button = v.findViewById(R.id.login_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail() && validatePassword()) {
                    FragmentManager fragmentManager = getParentFragmentManager() ;
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    signupFragment NAME = new signupFragment();
                    fragmentTransaction.replace(R.id.fragment_container, NAME);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getActivity(), "not success", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }
}