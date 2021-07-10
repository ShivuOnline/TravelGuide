package com.security.travelguide.views.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.galleryDetails.GalleryUploadMain;

public class PhotoViewer extends AppCompatActivity {

    private static final String TAG = "PhotoViewer";
    private TextView textPlaceType, textPlace, textPhotoName, textPhotoDate, textUserComments;
    private ImageView imagePhoto;
    private Button btnBack;

    private GalleryUploadMain galleryUploadMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (getIntent() != null) {
                galleryUploadMain = getIntent().getParcelableExtra(AppConstants.SELECTED_PLACE_ALL_DETAILS);
            }
            Log.d(TAG, "onCreate: galleryUploadMain: " + galleryUploadMain);

            setContentView(R.layout.activity_photo_viewer);

            setUpViews();

            loadPhotoDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        textPlaceType = findViewById(R.id.text_place_type_header);
        textPlace = findViewById(R.id.text_place_header);
        textPhotoName = findViewById(R.id.text_photo_name);
        textPhotoDate = findViewById(R.id.text_photo_capture_date);
        textUserComments = findViewById(R.id.text_comments);

        imagePhoto = findViewById(R.id.photo_image);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    callBackResult();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadPhotoDetails() {
        try {
            if (galleryUploadMain != null) {
                String placeName = galleryUploadMain.getPlace() + " (" + galleryUploadMain.getPlaceType() + ")";
                textPlace.setText(placeName);
                String photoName = galleryUploadMain.getPlacePhotoId() + ".jpg";
                textPhotoName.setText(photoName);
                textPhotoDate.setText(galleryUploadMain.getPlacePhotoUploadedDate());
                textUserComments.setText(galleryUploadMain.getUserComments());

                imagePhoto = findViewById(R.id.photo_image);

                Glide.with(imagePhoto)
                        .load(galleryUploadMain.getPlacePhotoPath())
                        .fitCenter()
                        .into(imagePhoto);
            } else {
                TravelGuideToast.showErrorToast(PhotoViewer.this, "Failed to load photo details", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callBackResult() {
        try {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}