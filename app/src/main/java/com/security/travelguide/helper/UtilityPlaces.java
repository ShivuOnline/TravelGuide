package com.security.travelguide.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.security.travelguide.model.PlaceItem;

import java.util.ArrayList;
import java.util.List;

public class UtilityPlaces {

    private static final String TAG = UtilityPlaces.class.getSimpleName();

    // Return Places Item List
    public static List<PlaceItem> getPlacesList(Context context, String placeTypeName) {
        List<PlaceItem> placeItemList = new ArrayList<>();
        try {
            switch (placeTypeName) {
                case UtilityConstants.DASHBOARD_ITEM_BEACHES:
                    placeItemList.clear();
                    List<String> beachesList = getBeachList();
                    int sliderCount = beachesList.size();
                    for (int position = 0; position < sliderCount; position++) {
                        String placeName = beachesList.get(position);
                        PlaceItem placeItem = new PlaceItem();
                        placeItem.setPlaceName(placeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        String iconName = "ic_beach_" + itemTitleWithLower;
                        Log.d(TAG, "getPlacesList: iconName: " + iconName);
                        placeItem.setPlaceImageDrawable(getImageFromDrawable(context, iconName));
                        placeItemList.add(placeItem);
                    }
                    break;
                case UtilityConstants.DASHBOARD_ITEM_HILL_STATION:
                    placeItemList.clear();
                    List<String> hillStationsList = getHillStationList();
                    int hillStationCount = hillStationsList.size();
                    for (int position = 0; position < hillStationCount; position++) {
                        String placeName = hillStationsList.get(position);
                        PlaceItem placeItem = new PlaceItem();
                        placeItem.setPlaceName(placeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        String iconName = "ic_hills_" + itemTitleWithLower;
                        Log.d(TAG, "getPlacesList: iconName: " + iconName);
                        placeItem.setPlaceImageDrawable(getImageFromDrawable(context, iconName));
                        placeItemList.add(placeItem);
                    }
                    break;
                case UtilityConstants.DASHBOARD_ITEM_MONUMENTS:
                    placeItemList.clear();
                    List<String> monumentsList = getMonumentsList();
                    int monumentsCount = monumentsList.size();
                    for (int position = 0; position < monumentsCount; position++) {
                        String placeName = monumentsList.get(position);
                        PlaceItem placeItem = new PlaceItem();
                        placeItem.setPlaceName(placeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        String iconName = "ic_monu_" + itemTitleWithLower;
                        Log.d(TAG, "getPlacesList: iconName: " + iconName);
                        placeItem.setPlaceImageDrawable(getImageFromDrawable(context, iconName));
                        placeItemList.add(placeItem);
                    }
                    break;
                case UtilityConstants.DASHBOARD_ITEM_RELIGIOUS:
                    placeItemList.clear();
                    List<String> religiousList = getReligiousList();
                    int religiousCount = religiousList.size();
                    for (int position = 0; position < religiousCount; position++) {
                        String placeName = religiousList.get(position);
                        PlaceItem placeItem = new PlaceItem();
                        placeItem.setPlaceName(placeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        String iconName = "ic_rel_" + itemTitleWithLower;
                        Log.d(TAG, "getPlacesList: iconName: " + iconName);
                        placeItem.setPlaceImageDrawable(getImageFromDrawable(context, iconName));
                        placeItemList.add(placeItem);
                    }
                    break;
                case UtilityConstants.DASHBOARD_ITEM_GARDENS:
                    placeItemList.clear();
                    List<String> gardensList = getGardensList();
                    int gardensCount = gardensList.size();
                    for (int position = 0; position < gardensCount; position++) {
                        String placeName = gardensList.get(position);
                        PlaceItem placeItem = new PlaceItem();
                        placeItem.setPlaceName(placeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        String iconName = "ic_gar_" + itemTitleWithLower;
                        Log.d(TAG, "getPlacesList: iconName: " + iconName);
                        placeItem.setPlaceImageDrawable(getImageFromDrawable(context, iconName));
                        placeItemList.add(placeItem);
                    }
                    break;
                case UtilityConstants.DASHBOARD_ITEM_WATER_FALLS:
                    placeItemList.clear();
                    List<String> waterFallsList = getWaterFallsList();
                    int waterFallsCount = waterFallsList.size();
                    for (int position = 0; position < waterFallsCount; position++) {
                        String placeName = waterFallsList.get(position);
                        PlaceItem placeItem = new PlaceItem();
                        placeItem.setPlaceName(placeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        String iconName = "ic_wat_" + itemTitleWithLower;
                        Log.d(TAG, "getPlacesList: iconName: " + iconName);
                        placeItem.setPlaceImageDrawable(getImageFromDrawable(context, iconName));
                        placeItemList.add(placeItem);
                    }
                    break;
            }
            return placeItemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return placeItemList;
    }

    private static List<String> getBeachList() {
        List<String> beachList = new ArrayList<>();
        beachList.add("Gokarna Beach");
        beachList.add("Karwar Beach");
        beachList.add("Kaup Beach Udupi");
        beachList.add("Malpe Beach Udupi");
        beachList.add("Om Beach Gokarna");
        beachList.add("Padubidri Beach");
        beachList.add("Sasitalu Beach Mangalore");
        return beachList;
    }

    private static List<String> getHillStationList() {
        List<String> hillStationList = new ArrayList<>();
        hillStationList.add("Kudure Mukha");
        hillStationList.add("Madikeri");
        hillStationList.add("Aagumbe");
        hillStationList.add("Nandhi Hills");
        hillStationList.add("Shivaganga Hills");
        hillStationList.add("Savandurga Hills");
        return hillStationList;
    }

    private static List<String> getMonumentsList() {
        List<String> monumentList = new ArrayList<>();
        monumentList.add("Golagumbaj Vijayapura");
        monumentList.add("Mysore Palace");
        monumentList.add("Vidhanasouda Bangalore");
        monumentList.add("Church Mysore");
        monumentList.add("Menabasid Badami");
        monumentList.add("Bidar Fort");
        return monumentList;
    }

    private static List<String> getReligiousList() {
        List<String> religiousList = new ArrayList<>();
        religiousList.add("Koodala Sangama");
        religiousList.add("Iskon Bangalore");
        religiousList.add("Murdeshwara");
        religiousList.add("Sri Krishna Uduppi");
        religiousList.add("Darmasthala Manjunatha");
        religiousList.add("Kukke Subramanya");
        religiousList.add("Chamundeshwari Mysore");
        return religiousList;
    }

    private static List<String> getGardensList() {
        List<String> gardensList = new ArrayList<>();
        gardensList.add("Alamatti Garden");
        gardensList.add("Hospet Garden");
        gardensList.add("Lalbag Bangalore");
        gardensList.add("Cubbon Park");
        gardensList.add("Mysore Zoo");
        gardensList.add("Dandeli Forest");
        return gardensList;
    }

    private static List<String> getWaterFallsList() {
        List<String> waterFallsList = new ArrayList<>();
        waterFallsList.add("Joga Falls");
        waterFallsList.add("Gokak Falls");
        waterFallsList.add("Godachina Malaki Falls");
        waterFallsList.add("KRS Water Dam");
        waterFallsList.add("Alamatti Water Dam");
        return waterFallsList;
    }

    public static Drawable getImageFromDrawable(Context context, String name) {
        return ResourcesCompat.getDrawable(context.getResources(), context.getResources().getIdentifier(name, "drawable", context.getPackageName()), null);
    }
}
