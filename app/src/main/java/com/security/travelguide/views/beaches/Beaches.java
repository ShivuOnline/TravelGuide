package com.security.travelguide.views.beaches;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.security.travelguide.R;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.UtilityPlaces;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.model.PlaceItem;
import com.security.travelguide.views.dashboard.Dashboard;

import java.util.List;

public class Beaches extends Fragment implements BeachesMainAdapter.PlaceItemClickListener {
    public static final String TAG = Beaches.class.getSimpleName();
    private View rootView;
    private OnFragmentInteractionListener listener;

    private RecyclerView recyclerBeachPlaces;
    private BeachesMainAdapter beachesMainAdapter;
    private TextView textTitle;

    public Beaches() {
        // Required empty public constructor
    }

    public static Beaches newInstance(Bundle bundle) {
        Beaches fragment = new Beaches();
        fragment.setArguments(bundle);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_beaches, container, false);
        textTitle = requireActivity().findViewById(R.id.title_header);
        if (textTitle != null) {
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(UtilityConstants.DASHBOARD_ITEM_BEACHES);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViews();
    }

    private void setUpViews() {
        try {

            recyclerBeachPlaces = rootView.findViewById(R.id.recycler_beaches);

            List<PlaceItem> beachPlaceItemList = UtilityPlaces.getPlacesList(requireContext(), UtilityConstants.DASHBOARD_ITEM_BEACHES);
            LinearLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 2);
            recyclerBeachPlaces.setLayoutManager(linearLayoutManager);
            beachesMainAdapter = new BeachesMainAdapter(requireContext(), beachPlaceItemList, this);
            recyclerBeachPlaces.setAdapter(beachesMainAdapter);

            beachesMainAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void beachPlaceItemClicked(int position, PlaceItem beachPlaceItem) {
        try {
//            Toast.makeText(requireContext(), "Beach Item: " + position, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}