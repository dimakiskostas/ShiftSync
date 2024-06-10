package com.example.shift.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shift.R;
import com.example.shift.databinding.FragmentGalleryBinding;
import com.example.shift.databinding.FragmentHomeBinding;
import com.example.shift.download.DownloadAvailability;
import com.example.shift.download.DownloadLogin;
import com.example.shift.download.DownloadShift;
import com.example.shift.download.DownloadShiftHome;
import com.example.shift.ui.gallery.GalleryViewModel;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import Client.Client;
import Config.Config;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ConstraintLayout shiftContainer;
    private ScrollView scrollView;
    private  int previousViewId = ViewGroup.NO_ID;
    private TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //shiftContainer = binding.getRoot();

        shiftContainer = root.findViewById(R.id.shiftContainer);

        textView = binding.textView11;

        //textView.setText("Your Shifts");
        textView.setGravity(Gravity.CENTER);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new FetchShiftDetailsTask().execute();
    }

    private void addShift(String shiftDetail) {
        TextView shiftTextView = new TextView(getContext());
        shiftTextView.setId(View.generateViewId());
        shiftTextView.setText(shiftDetail);
        shiftTextView.setPadding(16, 16, 16, 16);
        shiftTextView.setTextSize(16);
        shiftTextView.setGravity(Gravity.CENTER);
        shiftTextView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounder_back)); // Background color
        shiftTextView.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white)); // Text color
        shiftTextView.setTypeface(null, Typeface.BOLD); // Text style

        // Set layout parameters for margins
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50, 100, 50, 100);
        shiftTextView.setLayoutParams(layoutParams);

        shiftContainer.addView(shiftTextView);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(shiftContainer);

        if (previousViewId == View.NO_ID) {
            constraintSet.connect(shiftTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        } else {
            constraintSet.connect(shiftTextView.getId(), ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16);
        }

        constraintSet.connect(shiftTextView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        constraintSet.connect(shiftTextView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);

        constraintSet.applyTo(shiftContainer);

        previousViewId = shiftTextView.getId();
    }

    private class FetchShiftDetailsTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "defaultUsername");
            String password = sharedPreferences.getString("password", "defaultPassword");

            String response = null;

            try{
                Client client = new Client();
                response = client.sendrequest(Config.IP, Config.PORT,username,password);
            }catch (Exception e){
                e.printStackTrace();
            }


            List<String> shiftDetails = new ArrayList<>();

            String[] lines = response.split("\n");

            shiftDetails.add("Your Shifts");

            for (String line : lines) {
                shiftDetails.add(line);
            }
            // Simulate fetching data from a database or a server

            return shiftDetails;
        }

        @Override
        protected void onPostExecute(List<String> shiftDetails) {
            for (String shiftDetail : shiftDetails) {
                addShift(shiftDetail);
            }
        }
    }
}
