package com.example.shift.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shift.ui.Availability.AvailabilityViewModel;

import Client.Client;
import Config.Config;

public class DownloadDeleteAvailability extends AsyncTask<Void, Void, String> {


    private final Button button;
    private final EditText date;
    private final AvailabilityViewModel availabilityViewModel;
    private Context context;

    public DownloadDeleteAvailability(Button button, EditText date, AvailabilityViewModel availabilityViewModel, Context context) {
        this.button = button;
        this.date = date;
        this.availabilityViewModel = availabilityViewModel;
        this.context = context.getApplicationContext();
    }


    @Override
    protected String doInBackground(Void... voids) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "defaultUsername");
        String password = sharedPreferences.getString("password", "defaultPassword");

        String ans = null;

        try{
            Client client = new Client();
            ans = client.sendDeleteAvailability(Config.IP, Config.PORT, username, password, date.getText().toString());


        }catch (Exception e){
            e.printStackTrace();
        }

        return ans;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s.startsWith("Availability")){
            Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context.getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
