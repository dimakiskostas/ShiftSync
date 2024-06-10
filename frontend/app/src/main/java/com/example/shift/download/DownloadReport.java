package com.example.shift.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shift.ui.slideshow.SlideshowViewModel;

import Client.Client;
import Config.Config;

public class DownloadReport extends AsyncTask<Void, Void, String> {

    private final Button button;
    private final SlideshowViewModel slideshowViewModel;
    private EditText dateEditText;
    private EditText salesEditText;
    private EditText stockEditText;
    private EditText commentEditText;
    private Context context;

    public DownloadReport(Button button, SlideshowViewModel slideshowViewModel, EditText dateEditText, EditText salesEditText, EditText stockEditText, EditText commentEditText, Context context) {
        this.button = button;
        this.slideshowViewModel = slideshowViewModel;
        this.dateEditText = dateEditText;
        this.salesEditText = salesEditText;
        this.stockEditText = stockEditText;
        this.commentEditText = commentEditText;
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
            response = client.sendreport(Config.IP, Config.PORT,username, password, dateEditText.getText().toString(), salesEditText.getText().toString(), stockEditText.getText().toString(), commentEditText.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Toast.makeText(dateEditText.getContext(), s, Toast.LENGTH_SHORT).show();

//        if(s.startsWith("Report")){
//            Toast.makeText(commentEditText.getContext(), s, Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(commentEditText.getContext(), "Something went wrong. Please make sure you that you are using a date where you had a shift and the form of the input date is according to the example", Toast.LENGTH_SHORT).show();
//        }


        dateEditText.setText("e.g   25-05-2024");
        commentEditText.setText("Optional");
        salesEditText.setText("Sales");
        stockEditText.setText("Optional");
    }
}
