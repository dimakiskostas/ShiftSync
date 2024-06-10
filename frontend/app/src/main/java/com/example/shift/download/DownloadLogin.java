package com.example.shift.download;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.shift.R;
import com.example.shift.ui.Login.LoginViewModel;
import com.example.shift.ui.home.HomeViewModel;

import java.io.IOException;

import Client.Client;
import Config.Config;

public class DownloadLogin extends AsyncTask<Void, Void, String> {

    private final Button button;
    private final LoginViewModel loginViewModel;
    private final EditText editTextusername;
    private final EditText editTextpassword;
    private final View view;

    public DownloadLogin(Button button, LoginViewModel loginViewModel, EditText editTextusername, EditText editTextpassword, View view) {
        this.button = button;
        this.loginViewModel = loginViewModel;
        this.editTextusername = editTextusername;
        this.editTextpassword = editTextpassword;
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        button.setEnabled(false);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try{
            Client client = new Client();

            String ans = client.sendFileSignIn(Config.IP, Config.PORT, editTextusername.getText().toString(), editTextpassword.getText().toString());

            return ans;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void onPostExecute(String s) {
        button.setEnabled(true);

        if(s.startsWith("Welcome")){
            Navigation.findNavController(view).navigate(R.id.action_nav_log_in_to_nav_home);
        }else{
            Context context = view.getContext();
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
}
