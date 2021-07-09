package com.security.travelguide.views.gallery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.security.travelguide.R;
import com.security.travelguide.helper.FireBaseDatabaseConstants;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.model.galleryDetails.GalleryUploadMain;
import com.security.travelguide.model.userDetails.UserMain;

import java.util.ArrayList;
import java.util.List;

public class UserGallery extends Fragment implements UserGalleryMainAdapter.UserGalleryItemClickListener {

    private static final String TAG = "UserGallery";
    private View rootView;

    private RecyclerView userGalleryRecyclerView;
    private UserGalleryMainAdapter userGalleryMainAdapter;
    private TextView textNoPhotos;
    private ProgressDialog progressDialog;

    private List<GalleryUploadMain> galleryUploadMainList = new ArrayList<>();

    public UserGallery() {
        // Required empty public constructor
    }

    public static UserGallery createInstance(Bundle bundle) {
        UserGallery fragment = new UserGallery();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_gallery, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            progressDialog = new ProgressDialog(requireContext());

            UserMain loginUser = UserUtils.getLoginUserDetails(requireContext());

            loadGalleryPhotos(loginUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        try {
            userGalleryRecyclerView = rootView.findViewById(R.id.gallery_recycler);
            textNoPhotos = rootView.findViewById(R.id.text_no_photos);

            if (galleryUploadMainList.size() > 0) {
                textNoPhotos.setVisibility(View.GONE);
                userGalleryRecyclerView.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 2);
                userGalleryRecyclerView.setLayoutManager(linearLayoutManager);
                userGalleryMainAdapter = new UserGalleryMainAdapter(requireContext(), galleryUploadMainList, this);
                userGalleryRecyclerView.setAdapter(userGalleryMainAdapter);

                userGalleryMainAdapter.notifyDataSetChanged();
            } else {
                userGalleryRecyclerView.setVisibility(View.GONE);
                textNoPhotos.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadGalleryPhotos(UserMain userMain) {
        try {
            showProgressDialog("Loading gallery photos..");

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FireBaseDatabaseConstants.PHOTO_GALLERY_TABLE).child(userMain.getMobileNumber());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    try {
                        if(snapshot != null){
                            galleryUploadMainList.clear();
                            Log.d(TAG, "onDataChange: snapshot: " + snapshot.toString());
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                for (DataSnapshot dataSnapshot : postSnapshot.getChildren()) {
                                    Log.d(TAG, "onDataChange: dataSnapshot: " + dataSnapshot.toString());
                                    for (DataSnapshot subDataSnapshot : dataSnapshot.getChildren()) {
                                        GalleryUploadMain galleryUploadMain = subDataSnapshot.getValue(GalleryUploadMain.class);
                                        Log.d(TAG, "onDataChange: subDataSnapshot: " + galleryUploadMain);
                                        galleryUploadMainList.add(galleryUploadMain);
                                    }
                                }
                            }
                        }

                        hideProgressDialog();
                        setUpViews();
                    } catch (Exception e) {
                        e.printStackTrace();
                        hideProgressDialog();
                        setUpViews();
                        Log.d(TAG, "exception:" + e.getMessage());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    hideProgressDialog();
                    setUpViews();
                    Log.d(TAG, "onCancelled: failed to load user details");
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            setUpViews();
            Log.d(TAG, "loadAllUsers: exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showProgressDialog(String message) {
        try {
            if (progressDialog != null) {
                progressDialog.setMessage(message);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userItemItemClicked(int position, GalleryUploadMain galleryUploadMain) {

    }
}