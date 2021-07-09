package com.security.travelguide.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.security.travelguide.R;
import com.security.travelguide.model.DashboardItem;
import com.security.travelguide.model.SliderItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    // Return Dashboard Items List
    public static List<DashboardItem> getDashboardItemList(Context context) {
        List<String> itemTitleList = new ArrayList<>();
        itemTitleList.add(UtilityConstants.DASHBOARD_ITEM_BEACHES);
        itemTitleList.add(UtilityConstants.DASHBOARD_ITEM_HILL_STATION);
        itemTitleList.add(UtilityConstants.DASHBOARD_ITEM_MONUMENTS);
        itemTitleList.add(UtilityConstants.DASHBOARD_ITEM_RELIGIOUS);
        itemTitleList.add(UtilityConstants.DASHBOARD_ITEM_GARDENS);
        itemTitleList.add(UtilityConstants.DASHBOARD_ITEM_WATER_FALLS);

        List<DashboardItem> dashboardItemList = new ArrayList<>();

        for (int position = 0; position < itemTitleList.size(); position++) {

            DashboardItem dashboardItem = new DashboardItem();
            dashboardItem.setTitle(itemTitleList.get(position));
            String itemTitleWithLower = itemTitleList.get(position).replaceAll(" ", "_").toLowerCase();
            String iconName = "ic_" + itemTitleWithLower;
            Log.d(TAG, "getDashboardItemList: iconName: " + iconName);
            dashboardItem.setImageDrawable(getImageFromDrawable(context, iconName));

            dashboardItemList.add(dashboardItem);
        }
        return dashboardItemList;
    }

    // Return Slider Item List
    public static List<SliderItem> getSliderItemList(Context context) {
        int sliderCount = 5;
        List<SliderItem> sliderItemList = new ArrayList<>();
        for (int position = 1; position <= sliderCount; position++) {
            SliderItem sliderItem = new SliderItem();
            String iconName = "ic_slider_" + position;
            Log.d(TAG, "getSliderItemList: iconName: " + iconName);
            sliderItem.setImageDrawable(getImageFromDrawable(context, iconName));
            sliderItemList.add(sliderItem);
        }
        return sliderItemList;
    }

    public static Drawable getImageFromDrawable(Context context, String name) {
        try {
            return ResourcesCompat.getDrawable(context.getResources(), context.getResources().getIdentifier(name, "drawable", context.getPackageName()), null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResourcesCompat.getDrawable(context.getResources(),R.drawable.empty_image, null);
        }
    }

    public static String getCurrentTimeStampWithSeconds() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static List<String> getGenderType() {
        List<String> genderType = new ArrayList<>();
        genderType.add(AppConstants.MALE_GENDER);
        genderType.add(AppConstants.FEMALE_GENDER);
        genderType.add(AppConstants.OTHER_GENDER);
        return genderType;
    }
}