package com.example.shift.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shift.R;
import com.example.shift.databinding.FragmentSlideshowBinding;
import com.example.shift.download.DownloadReport;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private EditText stockEditText;
    private EditText commentEditText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        final Button reportButton = binding.button3;
        final EditText dateEditText = binding.editTextDate3;
        final EditText salesEditText = binding.editTextText;
        final EditText stockEditText = binding.editTextText2;
        final EditText commentEditText = binding.editTextText3;
        final ImageButton imageButton = binding.imageButton;


        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DownloadReport downloadReport = new DownloadReport(reportButton, slideshowViewModel, dateEditText, salesEditText, stockEditText, commentEditText, getContext());
                downloadReport.execute();
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_slideshow_to_nav_profile);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}