package com.security.travelguide.views.beaches;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.UtilityPlaces;
import com.security.travelguide.model.PlaceItem;
import com.security.travelguide.views.main.MainActivity;
import com.security.travelguide.views.main.MainViewActivity;
import com.security.travelguide.views.photoupload.PhotoUpload;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beachPlaceItemClicked(int position, ImageView imagePlace, TextView textPlaceHeader, PlaceItem beachPlaceItem) {
        try {
            Intent intent = new Intent(requireContext(), MainViewActivity.class);
            intent.putExtra(MainViewActivity.PLACES_ITEM_DETAILS, beachPlaceItem);

            Pair<View, String> transactionPairOne = Pair.create((View) imagePlace, requireContext().getResources().getString(R.string.transaction_name));
            Pair<View, String> transactionPairTwo = Pair.create((View) textPlaceHeader, requireContext().getResources().getString(R.string.transaction_title_name));

           /*
           // Call single Shared Transaction
           ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(requireActivity(), (View) imagePlace, requireContext().getResources().getString(R.string.transaction_name));
            */

            // Call Multiple Shared Transaction using Pair Option
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(requireActivity(), transactionPairOne, transactionPairTwo);
            startActivityForResult(intent, 123, options.toBundle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            MainActivity.hideBottomNav();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            MainActivity.showBottomNav();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 123 && resultCode == Activity.RESULT_OK && data != null) {
                String selectedPlaceType = data.getStringExtra(AppConstants.SELECTED_PLACE_TYPE);
                String selectedPlace = data.getStringExtra(AppConstants.SELECTED_PLACE);
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.SELECTED_PLACE_TYPE, selectedPlaceType);
                bundle.putString(AppConstants.SELECTED_PLACE, selectedPlace);
                listener.onFragmentInteraction(PhotoUpload.createInstance(bundle));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}