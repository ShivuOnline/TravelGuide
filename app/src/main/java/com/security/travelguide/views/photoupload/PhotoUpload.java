package com.security.travelguide.views.photoupload;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jakewharton.rxbinding.view.RxView;
import com.security.travelguide.R;
import com.security.travelguide.helper.FireBaseDatabaseConstants;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.UtilityPlaces;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.galleryDetails.GalleryUploadMain;
import com.security.travelguide.model.userDetails.UserMain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PhotoUpload extends Fragment {
    private static final String TAG = "PhotoUpload";
    private View rootView;
    private TextView textPlaceType, textPlace;
    private EditText editComments;
    private ImageView imageSelectedPhoto, imageCameraIcon;
    private Button btnSubmit;
    private Uri photoUploadUri;
    private ProgressDialog progressDialog;

    // Firebase Storage
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mUserReference;

    private StorageReference storageReference;

    private List<String> placeTypeList = new ArrayList<>();
    List<String> placeList = new ArrayList<>();


    public PhotoUpload() {
        // Required empty public constructor
    }

    public static PhotoUpload createInstance(Bundle bundle) {
        PhotoUpload fragment = new PhotoUpload();
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
        rootView = inflater.inflate(R.layout.fragment_photo_upload, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            progressDialog = new ProgressDialog(requireContext());

            firebaseDatabase = FirebaseDatabase.getInstance();
            mUserReference = FirebaseDatabase.getInstance().getReference(FireBaseDatabaseConstants.PHOTO_GALLERY_TABLE);
            storageReference = FirebaseStorage.getInstance().getReference();

            setUpViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        try {
            textPlaceType = rootView.findViewById(R.id.text_place_type);
            textPlace = rootView.findViewById(R.id.text_place);
            editComments = rootView.findViewById(R.id.edit_comments);
            imageSelectedPhoto = rootView.findViewById(R.id.place_image);
            imageCameraIcon = rootView.findViewById(R.id.image_camera_icon);
            btnSubmit = rootView.findViewById(R.id.btn_submit);

            imageSelectedPhoto.setImageDrawable(ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.empty_image, null));

            placeTypeList.clear();
            placeList.clear();

            placeTypeList = UtilityPlaces.getPlaceTypeList();

            RxView.touches(textPlaceType).subscribe(motionEvent -> {
                try {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(requireContext());
                        builderSingle.setTitle("Select Place Type");

                        final ArrayAdapter<String> placeTypeSelectionAdapter = new ArrayAdapter<String>(requireContext(),
                                android.R.layout.select_dialog_singlechoice, placeTypeList) {
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                TextView text = view.findViewById(android.R.id.text1);
                                text.setTextColor(Color.BLACK);
                                return view;
                            }
                        };

                        builderSingle.setNegativeButton("Cancel", (dialog, position) -> dialog.dismiss());

                        builderSingle.setAdapter(placeTypeSelectionAdapter, (dialog, position) -> {
                            textPlaceType.setText(placeTypeSelectionAdapter.getItem(position));
                        });
                        builderSingle.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


            RxView.touches(textPlace).subscribe(motionEvent -> {
                try {
                    String placeSelected = textPlaceType.getText().toString().trim();

                    if (placeSelected.isEmpty()) {
                        TravelGuideToast.showErrorToast(requireContext(), "Please select place type", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                        return;
                    }

                    placeList.clear();

                    switch (placeSelected) {
                        case UtilityConstants.DASHBOARD_ITEM_BEACHES:
                            placeList = UtilityPlaces.getBeachList();
                            break;
                        case UtilityConstants.DASHBOARD_ITEM_HILL_STATION:
                            placeList = UtilityPlaces.getHillStationList();
                            break;
                        case UtilityConstants.DASHBOARD_ITEM_MONUMENTS:
                            placeList = UtilityPlaces.getMonumentsList();
                            break;
                        case UtilityConstants.DASHBOARD_ITEM_RELIGIOUS:
                            placeList = UtilityPlaces.getReligiousList();
                            break;
                        case UtilityConstants.DASHBOARD_ITEM_GARDENS:
                            placeList = UtilityPlaces.getGardensList();
                            break;
                        case UtilityConstants.DASHBOARD_ITEM_WATER_FALLS:
                            placeList = UtilityPlaces.getWaterFallsList();
                            break;
                        default:
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(requireContext());
                        builderSingle.setTitle("Select Place");

                        final ArrayAdapter<String> placeSelectionAdapter = new ArrayAdapter<String>(requireContext(),
                                android.R.layout.select_dialog_singlechoice, placeList) {
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                TextView text = view.findViewById(android.R.id.text1);
                                text.setTextColor(Color.BLACK);
                                return view;
                            }
                        };

                        builderSingle.setNegativeButton("Cancel", (dialog, position) -> dialog.dismiss());

                        builderSingle.setAdapter(placeSelectionAdapter, (dialog, position) -> {
                            textPlace.setText(placeSelectionAdapter.getItem(position));
                        });
                        builderSingle.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validateFields()) {

                        UserMain loginUser = UserUtils.getLoginUserDetails(requireContext());

                        String userId = loginUser.getMobileNumber();
                        String selectedPlaceType = textPlaceType.getText().toString().trim();
                        String selectedPlace = textPlace.getText().toString().trim();

                        GalleryUploadMain galleryUploadMain = new GalleryUploadMain();
                        galleryUploadMain.setPlaceType(selectedPlaceType);
                        galleryUploadMain.setPlace(selectedPlace);
                        galleryUploadMain.setPlacePhotoId(Utils.getCurrentTimeStampWithSecondsAsId());
                        // Initial PhotoPath is Empty
                        galleryUploadMain.setPlacePhotoPath("");
                        galleryUploadMain.setPlacePhotoUploadedDate(Utils.getCurrentTimeStampWithSeconds());
                        galleryUploadMain.setUserComments(editComments.getText().toString().trim());
                        galleryUploadMain.setLatitude(0.0);
                        galleryUploadMain.setLongitude(0.0);

                        Log.d(TAG, "onClick: userId" + userId);
                        Log.d(TAG, "onClick: selectedPlaceType:" + selectedPlaceType);
                        Log.d(TAG, "onClick: selectedPlace:" + selectedPlace);
                        Log.d(TAG, "onClick: galleryUploadMain:" + galleryUploadMain);

                        upLoadPlacePhoto(galleryUploadMain, photoUploadUri, userId);

                    }
                }
            });

            imageCameraIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent galleryIntent = new Intent();
                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, 2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        try {
            if (textPlaceType.getText().toString().trim().isEmpty()) {
                TravelGuideToast.showErrorToast(requireContext(), "Please select place type", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (textPlace.getText().toString().trim().isEmpty()) {
                TravelGuideToast.showErrorToast(requireContext(), "Please select place", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (photoUploadUri == null) {
                TravelGuideToast.showErrorToast(requireContext(), "Please select photo", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (editComments.getText().toString().trim().isEmpty()) {
                TravelGuideToast.showErrorToast(requireContext(), "Please enter comments", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TravelGuideToast.showErrorToast(requireContext(), "Exception occurred, please try again.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
            return false;
        }
    }

    private void upLoadPlacePhoto(GalleryUploadMain galleryUploadMain, Uri photoUploadUri, String userId) {
        try {
            showProgressDialog("Processing your request..");

            StorageReference fileRef = storageReference.child(galleryUploadMain.getPlacePhotoId() + "." + getFileExtension(photoUploadUri));
            fileRef.putFile(photoUploadUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            galleryUploadMain.setPlacePhotoPath(uri.toString());
                            hideProgressDialog();
                            submitPhotoDetails(galleryUploadMain, userId);
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    hideProgressDialog();
                    TravelGuideToast.showErrorToast(requireContext(), "Failed to upload photo", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            hideProgressDialog();
            TravelGuideToast.showErrorToast(requireContext(), e.getMessage(), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    public void submitPhotoDetails(GalleryUploadMain galleryUploadMain, String userId) {
        try {
            showProgressDialog("Submitting please wait.");

            mUserReference.child(userId).child(galleryUploadMain.getPlaceType()).child(galleryUploadMain.getPlace()).child(galleryUploadMain.getPlacePhotoId()).setValue(galleryUploadMain)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            hideProgressDialog();
                            TravelGuideToast.showSuccessToast(requireContext(), "Photo submitted successfully.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_LONG);
                            clearAllFields();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideProgressDialog();
                            TravelGuideToast.showErrorToast(requireContext(), "Failed to submit photo details", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                        }
                    });
        } catch (Exception e) {
            hideProgressDialog();
            TravelGuideToast.showErrorToast(requireContext(), e.getMessage(), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    private void clearAllFields() {
        try {
            textPlaceType.setText("");
            textPlace.setText("");
            photoUploadUri = null;
            editComments.setText("");
            imageSelectedPhoto.setImageURI(null);
            imageSelectedPhoto.setImageDrawable(ResourcesCompat.getDrawable(requireContext().getResources(), R.drawable.empty_image, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(Uri profilePicUri) {
        ContentResolver contentResolver = requireActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(profilePicUri));
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            photoUploadUri = data.getData();
            imageSelectedPhoto.setImageURI(photoUploadUri);
        }

    }
}