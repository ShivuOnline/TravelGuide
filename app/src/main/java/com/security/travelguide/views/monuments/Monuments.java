package com.security.travelguide.views.monuments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.security.travelguide.R;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.UtilityPlaces;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.model.PlaceItem;
import com.security.travelguide.views.main.MainViewActivity;

import java.util.List;

public class Monuments extends Fragment implements MonumentsMainAdapter.PlaceItemClickListener {
    private static final String TAG = Monuments.class.getSimpleName();

    private View rootView;
    private OnFragmentInteractionListener listener;

    private RecyclerView recyclerMonuments;
    private MonumentsMainAdapter monumentsMainAdapter;
    private TextView textTitle;


    public Monuments() {
        // Required empty public constructor
    }


    public static Monuments newInstance(Bundle bundle) {
        Monuments fragment = new Monuments();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_monuments, container, false);
        textTitle = requireActivity().findViewById(R.id.title_header);
        if (textTitle != null) {
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(UtilityConstants.DASHBOARD_ITEM_MONUMENTS);
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
            recyclerMonuments = rootView.findViewById(R.id.recycler_monuments);

            List<PlaceItem> monumentsPlaceItemList = UtilityPlaces.getPlacesList(requireContext(), UtilityConstants.DASHBOARD_ITEM_MONUMENTS);
            LinearLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 2);
            recyclerMonuments.setLayoutManager(linearLayoutManager);
            monumentsMainAdapter = new MonumentsMainAdapter(requireContext(), monumentsPlaceItemList, this);
            recyclerMonuments.setAdapter(monumentsMainAdapter);

            monumentsMainAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void monumentsPlaceItemClicked(int position, ImageView imagePlace, TextView textPlaceHeader, PlaceItem monumentsPlaceItem) {
        try {
            Intent intent = new Intent(requireContext(), MainViewActivity.class);
            intent.putExtra(MainViewActivity.PLACES_ITEM_DETAILS, monumentsPlaceItem);
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
            startActivity(intent, options.toBundle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}