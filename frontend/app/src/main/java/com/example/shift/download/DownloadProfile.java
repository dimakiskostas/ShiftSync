package com.example.shift.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.shift.ui.Profile.ProfileViewModel;

import Client.Client;
import Config.Config;

public class DownloadProfile extends AsyncTask<Void, Void, String> {

    private Context context;
    private TextView name;
    private final ProfileViewModel profileViewModel;

    public DownloadProfile(Context context, TextView name, ProfileViewModel profileViewModel) {
        this.context = context.getApplicationContext();
        this.name = name;
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
            response = client.sendFileForName(Config.IP, Config.PORT,username, password);
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        name.setText(s);
    }
}
