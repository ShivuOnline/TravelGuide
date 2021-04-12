package com.security.travelguide.views.main;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.security.travelguide.R;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.model.PlaceItem;

public class MainViewActivity extends Activity {
    private static final String TAG = MainViewActivity.class.getSimpleName();
    public static final String PLACES_ITEM_DETAILS = "Places Item Details";

    private ScrollView scrollView;
    private ImageView placesImage;
    private TextView textHeaderPrimary, textHeaderSecondary, textPlacesDesc;
    private PlaceItem placeItemMain;

    private CardView cardGoogleInfo, cardGmapDirection;
    private TextView textGoogleInfo, textGmapDirection;

    public MainViewActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.fragment_main_view);

            if (getIntent() != null) {
                placeItemMain = getIntent().getParcelableExtra(PLACES_ITEM_DETAILS);
                Log.d(TAG, "onCreate: placeItemMain" + placeItemMain);
            }

            setUpViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        try {
            scrollView = findViewById(R.id.scrollView);
            placesImage = findViewById(R.id.places_image);
            textHeaderSecondary = findViewById(R.id.text_header_secondary);
            textPlacesDesc = findViewById(R.id.places_description);
            textHeaderPrimary = findViewById(R.id.text_header_primary);

            cardGoogleInfo = findViewById(R.id.google_info_item_cardview);
            cardGmapDirection = findViewById(R.id.gmap_direction_item_cardview);

            textGoogleInfo = findViewById(R.id.text_google_info);
            textGmapDirection = findViewById(R.id.text_gmap_direction);

            // Initially hide the textHeaderPrimary and Visible textHeaderSecondary view
            textHeaderPrimary.setAlpha(0f);
            textHeaderSecondary.setAlpha(1f);
            /* set the scroll change listener on scrollview */
            scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {

                    /* get the maximum height which we have scroll before performing any action */
                    int maxDistance = placesImage.getHeight();
                    /* how much we have scrolled */
                    int movement = scrollView.getScrollY();
                    /*finally calculate the alpha factor and set on the view */
                    float alphaFactor = ((movement * 1.0f) / (maxDistance - textHeaderPrimary.getHeight()));
                    if (movement >= 0 && movement <= maxDistance) {
                        /*for image parallax with scroll */
                        placesImage.setTranslationY(-movement / 2);
                        /* set visibility */
                        textHeaderPrimary.setAlpha(alphaFactor);

                        // Max alpha is 1.0f (Visible) and min 0.0f (Hide)
                        float reverseAlphaForSecondary = 1f - alphaFactor;
                        textHeaderSecondary.setAlpha(reverseAlphaForSecondary);

                    }
                }
            });

            if (placeItemMain != null) {
                String placeName = placeItemMain.getPlaceName();
                textHeaderPrimary.setText(placeName);
                textHeaderSecondary.setText(placeName);
                String placeDescription = placeItemMain.getPlaceDescription();
                textPlacesDesc.setText(placeDescription);

                String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                String iconName = "";
                String staticPlaceType = placeItemMain.getStaticPlaceType();
                switch (staticPlaceType) {
                    case UtilityConstants.DASHBOARD_ITEM_BEACHES:
                        iconName = "ic_beach_" + itemTitleWithLower;
                        break;
                    case UtilityConstants.DASHBOARD_ITEM_HILL_STATION:
                        iconName = "ic_hills_" + itemTitleWithLower;
                        break;
                    case UtilityConstants.DASHBOARD_ITEM_MONUMENTS:
                        iconName = "ic_monu_" + itemTitleWithLower;
                        break;
                    case UtilityConstants.DASHBOARD_ITEM_RELIGIOUS:
                        iconName = "ic_rel_" + itemTitleWithLower;
                        break;
                    case UtilityConstants.DASHBOARD_ITEM_GARDENS:
                        iconName = "ic_gar_" + itemTitleWithLower;
                        break;
                    case UtilityConstants.DASHBOARD_ITEM_WATER_FALLS:
                        iconName = "ic_wat_" + itemTitleWithLower;
                        break;
                }

                Glide.with(placesImage)
                        .load(getImageFromDrawable(MainViewActivity.this, iconName))
                        .fitCenter()
                        .centerCrop()
                        .into(placesImage);
            }

            // Open Google for More Info
            cardGoogleInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        openGoogleInfo(MainViewActivity.this, placeItemMain.getPlaceReferenceLink());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            textGoogleInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        openGoogleInfo(MainViewActivity.this, placeItemMain.getPlaceReferenceLink());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // Open GooGle Map for Direction
            cardGmapDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        openGoogleMapForDirection(MainViewActivity.this, placeItemMain.getPlaceLatitude(), placeItemMain.getPlaceLongitude());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            textGmapDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        openGoogleMapForDirection(MainViewActivity.this, placeItemMain.getPlaceLatitude(), placeItemMain.getPlaceLongitude());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openGoogleInfo(Context context, String placeInfoUrl) {
        Log.d(TAG, "openGoogleInfo: placeInfoUrl: "+placeInfoUrl);
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeInfoUrl));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed and open Default Browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeInfoUrl));
                startActivity(browserIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openGoogleMapForDirection(Context context, double placeLatitude, double placeLongitude) {
        try {
            // By Default set Google Map
            String mapRequest = "google.navigation:q=" + placeLatitude + "," + placeLongitude + "&avoid=tf";
            Uri gmmIntentUri = Uri.parse(mapRequest);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Drawable getImageFromDrawable(Context context, String name) {
        return ResourcesCompat.getDrawable(context.getResources(), context.getResources().getIdentifier(name, "drawable", context.getPackageName()), null);
    }
}