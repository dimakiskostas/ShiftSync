package com.example.shift.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.shift.ui.home.HomeViewModel;

import Client.Client;
import Config.Config;

public class DownloadShiftHome extends AsyncTask<Void, Void, String> {


    private Context context;
    private TextView shift;
    private HomeViewModel homeViewModel;

    public DownloadShiftHome(Context context, TextView shift, HomeViewModel homeViewModel) {
        this.context = context;
        this.shift = shift;
        this.homeViewModel = homeViewModel;
    }


    @Override
    protected String doInBackground(Void... voids) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "defaultUsername");
        String password = sharedPreferences.getString("password", "defaultPassword");

        String response = null;

        try{
            Client client = new Client();
            response = client.sendrequest(Config.IP, Config.PORT,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        String str = getLastTwoLines(s);
        super.onPostExecute(s);
        shift.setText(str);

    }

    public static String getLastLine(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        String[] lines = s.split("\\r?\\n");
        return lines.length > 0 ? lines[lines.length - 1] : "";
    }


    public static String getLastTwoLines(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        String[] lines = s.split("\\r?\\n");

        if (lines.length == 0) {
            return "";
        } else if (lines.length == 1) {
            return lines[0];
        } else {
            return lines[lines.length - 2] + "\n" + lines[lines.length - 1];
        }
    }
}
