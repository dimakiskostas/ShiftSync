package com.example.shift.ui.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shift.R;
import com.example.shift.databinding.FragmentProfileBinding;
import com.example.shift.download.DownloadProfile;
import com.example.shift.download.DownloadProfileNumber;
import com.example.shift.download.DownloadShift;
import com.example.shift.ui.gallery.GalleryViewModel;
import com.example.shift.ui.home.HomeViewModel;

import Client.Client;
import Config.Config;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Context context;

    private LinearLayout container;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView name = binding.textView9;

        final TextView phone = binding.textView10;

        final TextView shift = binding.textView12;


        DownloadProfile downloadProfile = new DownloadProfile(getContext(), name, profileViewModel);
        downloadProfile.execute();

        DownloadProfileNumber downloadProfileNumber = new DownloadProfileNumber(getContext(), phone, profileViewModel);
        downloadProfileNumber.execute();

        DownloadShift downloadShift = new DownloadShift(getContext(),shift,profileViewModel);
        downloadShift.execute();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
