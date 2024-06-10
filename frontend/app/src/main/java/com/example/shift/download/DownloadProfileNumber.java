package com.example.shift.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.shift.ui.Profile.ProfileFragment;
import com.example.shift.ui.Profile.ProfileViewModel;

import Client.Client;
import Config.Config;

public class DownloadProfileNumber extends AsyncTask<Void, Void, String> {

    private Context context;
    private TextView number;
    private final ProfileViewModel profileViewModel;

    public DownloadProfileNumber(Context context, TextView number, ProfileViewModel profileViewModel) {
        this.context = context;
        this.number = number;
        this.profileViewModel = profileViewModel;
    }

    @Override
    protected String doInBackground(Void... voids) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "defaultUsername");
        String password = sharedPreferences.getString("password", "defaultPassword");


        String response = null;

        try{
            Client client = new Client();
            response = client.sendFileForPhone(Config.IP, Config.PORT,username, password);
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        number.setText(s);
    }
}
