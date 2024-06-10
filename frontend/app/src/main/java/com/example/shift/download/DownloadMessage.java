package com.example.shift.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shift.ui.gallery.GalleryViewModel;
import com.example.shift.ui.home.HomeViewModel;

import java.lang.ref.Cleaner;

import Client.Client;
import Config.Config;

public class DownloadMessage extends AsyncTask<Void, Void, String> {

    private final Button button ;
    private final GalleryViewModel messageHomeView;
    private EditText editText;

    private Context context;

    public DownloadMessage(Button button, GalleryViewModel messageHomeView, EditText editText, Context context) {
        this.button = button;
        this.messageHomeView = messageHomeView;
        this.editText = editText;
        this.context = context.getApplicationContext();
    }


    @Override
    protected String doInBackground(Void... voids) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "defaultUsername");
        String password = sharedPreferences.getString("password", "defaultPassword");

        String response = null;
        try{
            Client client = new Client();
            response = client.sendalert(Config.IP, Config.PORT, username, password, editText.getText().toString());


        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }


    @Override
    protected void onPostExecute(String s) {

        if(s.startsWith("Alert")){
            Toast.makeText(editText.getContext(), s, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(editText.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        editText.setText(" ");
    }
}
