package com.security.travelguide.views.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.security.travelguide.BuildConfig;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.views.dashboard.Dashboard;
import com.security.travelguide.views.profile.Profile;
import com.security.travelguide.views.updateMpin.UpdateMPin;

import java.util.ArrayList;
import java.util.List;


public class Settings extends Fragment implements SettingsMainAdapter.SettingsItemClickListener {
    private static final String TAG = "Settings";
    private View rootView;
    private TextView textVersion;
    private List<String> settingsOptionList = new ArrayList<>();
    private RecyclerView recyclerSettingOption;
    private SettingsMainAdapter settingsMainAdapter;

    private OnFragmentInteractionListener listener;

    public Settings() {
        // Required empty public constructor
    }

    public static Settings createInstance(Bundle bundle) {
        Settings fragment = new Settings();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            if (context instanceof Dashboard.OnFragmentInteractionListener) {
                listener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + context.getResources().getString(R.string.implementation_on_fragment_listener));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment fragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            settingsOptionList = Utils.getSettingsOption();

            setUpViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        try {
            textVersion = rootView.findViewById(R.id.text_version);
            recyclerSettingOption = rootView.findViewById(R.id.recycler_setting_option);

            LinearLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 1);
            recyclerSettingOption.setLayoutManager(linearLayoutManager);
            settingsMainAdapter = new SettingsMainAdapter(requireContext(), settingsOptionList, this);
            recyclerSettingOption.setAdapter(settingsMainAdapter);

            settingsMainAdapter.notifyDataSetChanged();

            String version = "Version." + BuildConfig.VERSION_NAME;
            textVersion.setText(version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void settingsItemClicked(int position, String item) {
        try {
            switch (item) {
                case AppConstants
                        .SETTINGS_PROFILE:
                    listener.onFragmentInteraction(new Profile());
                    break;
                case AppConstants
                        .SETTINGS_UPDATE_MPIN:
                    listener.onFragmentInteraction(new UpdateMPin());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}