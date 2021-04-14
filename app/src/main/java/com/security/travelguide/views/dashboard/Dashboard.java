package com.security.travelguide.views.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.security.travelguide.R;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.model.DashboardItem;
import com.security.travelguide.model.SliderItem;
import com.security.travelguide.views.beaches.Beaches;
import com.security.travelguide.views.gardens.Gardens;
import com.security.travelguide.views.hillstations.HillStations;
import com.security.travelguide.views.main.MainActivity;
import com.security.travelguide.views.monuments.Monuments;
import com.security.travelguide.views.religious.Religious;
import com.security.travelguide.views.waterfalls.WaterFalls;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class Dashboard extends Fragment implements DashboardMainAdapter.DashboardItemClickListener {

    private static final String TAG = Dashboard.class.getSimpleName();
    private View rootView;
    private OnFragmentInteractionListener listener;

    private RecyclerView recyclerDashboard;
    private DashboardMainAdapter dashboardMainAdapter;
    private TextView textTitle;

    public Dashboard() {
        // Required empty public constructor
    }

    public static Dashboard newInstance(Bundle bundle) {
        Dashboard fragment = new Dashboard();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textTitle = requireActivity().findViewById(R.id.title_header);
        if (textTitle != null) {
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(R.string.app_name);
        }
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            if (context instanceof OnFragmentInteractionListener) {
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViews();
    }

    private void setUpViews() {
        try {
            recyclerDashboard = rootView.findViewById(R.id.recycler_dashboard);
            setSliderView();
            List<DashboardItem> dashboardItemList = Utils.getDashboardItemList(requireContext());
            LinearLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 2);
            recyclerDashboard.setLayoutManager(linearLayoutManager);
            dashboardMainAdapter = new DashboardMainAdapter(requireContext(), dashboardItemList, this);
            recyclerDashboard.setAdapter(dashboardMainAdapter);

            dashboardMainAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSliderView() {
        try {
            SliderView sliderView = rootView.findViewById(R.id.imageSlider);

            DashboardSliderAdapter adapter = new DashboardSliderAdapter(requireContext());

            List<SliderItem> sliderItemList = Utils.getSliderItemList(requireContext());

            adapter.renewItems(sliderItemList);

            sliderView.setSliderAdapter(adapter);

            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.WHITE);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dashboardItemClicked(int position, DashboardItem dashboardItem) {
        try {
            String itemSelectedName = dashboardItem.getTitle();
            switch (itemSelectedName) {
                case UtilityConstants
                        .DASHBOARD_ITEM_BEACHES:
                    listener.onFragmentInteraction(new Beaches());
                    break;
                case UtilityConstants
                        .DASHBOARD_ITEM_HILL_STATION:
                    listener.onFragmentInteraction(new HillStations());
                    break;
                case UtilityConstants
                        .DASHBOARD_ITEM_MONUMENTS:
                    listener.onFragmentInteraction(new Monuments());
                    break;
                case UtilityConstants
                        .DASHBOARD_ITEM_RELIGIOUS:
                    listener.onFragmentInteraction(new Religious());
                    break;
                case UtilityConstants
                        .DASHBOARD_ITEM_GARDENS:
                    listener.onFragmentInteraction(new Gardens());
                    break;
                case UtilityConstants
                        .DASHBOARD_ITEM_WATER_FALLS:
                    listener.onFragmentInteraction(new WaterFalls());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}