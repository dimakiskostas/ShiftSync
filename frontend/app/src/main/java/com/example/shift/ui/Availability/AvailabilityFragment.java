package com.example.shift.ui.Availability;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.shift.R;
import com.example.shift.databinding.FragmentAvailabilityBinding;
import com.example.shift.download.DownloadAvail;
import com.example.shift.download.DownloadAvailability;
import com.example.shift.download.DownloadDeleteAvailability;
import com.example.shift.ui.gallery.GalleryViewModel;
import com.example.shift.ui.home.HomeFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Client.Client;
import Config.Config;

public class AvailabilityFragment extends Fragment {

    private FragmentAvailabilityBinding binding;

    private ConstraintLayout availabilitycontainer;
    private  int previousViewId = ViewGroup.NO_ID;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AvailabilityViewModel availabilityViewModel =
                new ViewModelProvider(this).get(AvailabilityViewModel.class);

        binding = FragmentAvailabilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        ImageButton imageButton = binding.imageButton3;
        //TextView textView = binding.textView14;

        Button button = binding.button4;

        EditText date = binding.editTextDate2;

        EditText starttime = binding.editTextTime3;
        EditText endtime = binding.editTextTime4;

        EditText deleteDate = binding.editTextDate;
        Button deleteButton = binding.button;

        availabilitycontainer = root.findViewById(R.id.AvailabilityContainer);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadAvailability downloadAvailability = new DownloadAvailability(button, date, starttime, endtime,availabilityViewModel, getContext());
                downloadAvailability.execute();

            }
        });



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_availability_to_nav_profile);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadDeleteAvailability downloadDeleteAvailability = new DownloadDeleteAvailability(button, deleteDate, availabilityViewModel,getContext());
                downloadDeleteAvailability.execute();
            }
        });


        return root;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new FetchAvailabilityDetailsTask().execute();
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

        availabilitycontainer.addView(shiftTextView);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(availabilitycontainer);

        if (previousViewId == View.NO_ID) {
            constraintSet.connect(shiftTextView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        } else {
            constraintSet.connect(shiftTextView.getId(), ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16);
        }

        constraintSet.connect(shiftTextView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        constraintSet.connect(shiftTextView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);

        constraintSet.applyTo(availabilitycontainer);

        previousViewId = shiftTextView.getId();
    }

    private class FetchAvailabilityDetailsTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "defaultUsername");
            String password = sharedPreferences.getString("password", "defaultPassword");

            String response = null;

            try{
                Client client = new Client();
                response = client.sendrequestavailability(Config.IP, Config.PORT,username,password);
            }catch (Exception e){
                e.printStackTrace();
            }


            List<String> shiftDetails = new ArrayList<>();

            String[] lines = response.split("\n");

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
