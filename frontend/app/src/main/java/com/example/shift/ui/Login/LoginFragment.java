package com.example.shift.ui.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.shift.R;
import com.example.shift.databinding.FragmentHomeBinding;
import com.example.shift.databinding.FragmentLoginBinding;
import com.example.shift.download.DownloadLogin;

import java.security.cert.CertPath;
import java.util.concurrent.ExecutionException;

import Client.Client;
import Config.Config;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button LoginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameEditText = view.findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = view.findViewById(R.id.editTextTextPassword);
        LoginButton = view.findViewById(R.id.buttonLogin);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                DownloadLogin downloadLogin = new DownloadLogin(LoginButton,loginViewModel,usernameEditText, passwordEditText, view);

                downloadLogin.execute();
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
