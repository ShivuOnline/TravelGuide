package com.security.travelguide.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.security.travelguide.model.PlaceItem;

import java.util.ArrayList;
import java.util.List;

import static com.security.travelguide.helper.UtilityPlaceConstants.B_GOKARNA_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_KARWAR_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_KAUP_BEACH_UDUPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_MALPE_BEACH_UDUPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_OM_BEACH_GOKARNA;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_PADUBIDRI_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_SASIHITHLU_BEACH_MANGALORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_ALAMATTI_GARDEN;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_CUBBON_PARK;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_DANDELI_FOREST;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_HOSPET_GARDEN;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_LALBAG_BANGALORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_MYSORE_ZOO;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_AGUMBE;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_KUDREMUKH;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_MADIKERI;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_NANDHI_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_SAVANADURGA_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_SHIVAGANGE_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_BIDAR_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_MENA_BASADI;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_PHILOMENA_CHURCH;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_GOL_GUMBAZ;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_MENA_BASADI;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_MYSORE_PALACE;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_VIDHANA_SOUDHA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_CHAMUNDESHWARI_MYSORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_DARMASTHALA_MANJUNATHA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_ISKON_BANGALORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KOODALA_SANGAMA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KUKKE_SUBRAMANYA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_MURUDESHWARA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_SRI_KRISHNA_UDUPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_ALAMATTI_WATER_DAM;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_GODACHINA_MALAKI_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_GOKAK_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_JOGA_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_KRS_WATER_DAM;

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
                        // Set Place Name
                        placeItem.setPlaceName(placeName);
                        // Set PlaceType
                        placeItem.setStaticPlaceType(placeTypeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        // Set Place Description
                        String discStringKey = "beaches_" + itemTitleWithLower + "_desc";
                        String desc = getDescStringFromResource(context, discStringKey);
                        placeItem.setPlaceDescription(desc);
                        // Set Place Drawable Image ID
                        String iconName = "ic_beach_" + itemTitleWithLower;
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
                        // Set Place Name
                        placeItem.setPlaceName(placeName);
                        // Set Place Type
                        placeItem.setStaticPlaceType(placeTypeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        // Set Place Description
                        String discStringKey = "hills_" + itemTitleWithLower + "_desc";
                        String desc = getDescStringFromResource(context, discStringKey);
                        placeItem.setPlaceDescription(desc);
                        String iconName = "ic_hills_" + itemTitleWithLower;
                        // Set Place Image Drawable Id
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
                        // Set Place Name
                        placeItem.setPlaceName(placeName);
                        // Set Place Type
                        placeItem.setStaticPlaceType(placeTypeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        // Set Place Description
                        String discStringKey = "monumnets_" + itemTitleWithLower + "_desc";
                        String desc = getDescStringFromResource(context, discStringKey);
                        placeItem.setPlaceDescription(desc);
                        String iconName = "ic_monu_" + itemTitleWithLower;
                        // Set Place IMage Drawable Id
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
                        // Set Place Name
                        placeItem.setPlaceName(placeName);
                        // Set Place Type
                        placeItem.setStaticPlaceType(placeTypeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        // Set Place Description
                        String discStringKey = "rel_" + itemTitleWithLower + "_desc";
                        String desc = getDescStringFromResource(context, discStringKey);
                        placeItem.setPlaceDescription(desc);
                        String iconName = "ic_rel_" + itemTitleWithLower;
                        // Set Place Image Drawable Id
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
                        // Set Place Name
                        placeItem.setPlaceName(placeName);
                        //Set Place Type
                        placeItem.setStaticPlaceType(placeTypeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        // Set Place Description
                        String discStringKey = "gard_" + itemTitleWithLower + "_desc";
                        String desc = getDescStringFromResource(context, discStringKey);
                        placeItem.setPlaceDescription(desc);
                        String iconName = "ic_gar_" + itemTitleWithLower;
                        // Set Place Image Drawable Id
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
                        // Set Place Name
                        placeItem.setPlaceName(placeName);
                        // Set Place Type
                        placeItem.setStaticPlaceType(placeTypeName);
                        String itemTitleWithLower = placeName.replaceAll(" ", "_").toLowerCase();
                        // Set Place Description
                        String discStringKey = "water_" + itemTitleWithLower + "_desc";
                        String desc = getDescStringFromResource(context, discStringKey);
                        placeItem.setPlaceDescription(desc);
                        String iconName = "ic_wat_" + itemTitleWithLower;
                        // Set Place Image Drawable Id
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
        // Beaches Names
        List<String> beachList = new ArrayList<>();
        beachList.add(B_GOKARNA_BEACH);
        beachList.add(B_KARWAR_BEACH);
        beachList.add(B_KAUP_BEACH_UDUPI);
        beachList.add(B_MALPE_BEACH_UDUPI);
        beachList.add(B_OM_BEACH_GOKARNA);
        beachList.add(B_PADUBIDRI_BEACH);
        beachList.add(B_SASIHITHLU_BEACH_MANGALORE);
        return beachList;
    }

    private static List<String> getHillStationList() {
        // Hills Station Names
        List<String> hillStationList = new ArrayList<>();
        hillStationList.add(H_KUDREMUKH);
        hillStationList.add(H_MADIKERI);
        hillStationList.add(H_AGUMBE);
        hillStationList.add(H_NANDHI_HILLS);
        hillStationList.add(H_SHIVAGANGE_HILLS);
        hillStationList.add(H_SAVANADURGA_HILLS);
        return hillStationList;
    }

    private static List<String> getMonumentsList() {
        // Monuments Names
        List<String> monumentList = new ArrayList<>();
        monumentList.add(M_GOL_GUMBAZ);
        monumentList.add(M_MYSORE_PALACE);
        monumentList.add(M_VIDHANA_SOUDHA);
        monumentList.add(M_PHILOMENA_CHURCH);
        monumentList.add(M_MENA_BASADI);
        monumentList.add(M_BIDAR_FORT);
        return monumentList;
    }

    private static List<String> getReligiousList() {
        // Religious Names
        List<String> religiousList = new ArrayList<>();
        religiousList.add(R_KOODALA_SANGAMA);
        religiousList.add(R_ISKON_BANGALORE);
        religiousList.add(R_MURUDESHWARA);
        religiousList.add(R_SRI_KRISHNA_UDUPI);
        religiousList.add(R_DARMASTHALA_MANJUNATHA);
        religiousList.add(R_KUKKE_SUBRAMANYA);
        religiousList.add(R_CHAMUNDESHWARI_MYSORE);
        return religiousList;
    }

    private static List<String> getGardensList() {
        // Gardens Names
        List<String> gardensList = new ArrayList<>();
        gardensList.add(G_ALAMATTI_GARDEN);
        gardensList.add(G_HOSPET_GARDEN);
        gardensList.add(G_LALBAG_BANGALORE);
        gardensList.add(G_CUBBON_PARK);
        gardensList.add(G_MYSORE_ZOO);
        gardensList.add(G_DANDELI_FOREST);
        return gardensList;
    }

    private static List<String> getWaterFallsList() {
        // Water Falls Names
        List<String> waterFallsList = new ArrayList<>();
        waterFallsList.add(W_JOGA_FALLS);
        waterFallsList.add(W_GOKAK_FALLS);
        waterFallsList.add(W_GODACHINA_MALAKI_FALLS);
        waterFallsList.add(W_KRS_WATER_DAM);
        waterFallsList.add(W_ALAMATTI_WATER_DAM);
        return waterFallsList;
    }

    public static Drawable getImageFromDrawable(Context context, String name) {
        try {
            return ResourcesCompat.getDrawable(context.getResources(), context.getResources().getIdentifier(name, "drawable", context.getPackageName()), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResourcesCompat.getDrawable(context.getResources(), context.getResources().getIdentifier("empty_image", "drawable", context.getPackageName()), null);
    }

    public static String getDescStringFromResource(Context context, String stringKey) {
        try {
            // Get the identifier of the resource by its name.
            @StringRes int resId = context.getResources().getIdentifier(stringKey, "string", context.getPackageName());
            // Use the value of the resource.
            return context.getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getReferenceUrls(String placeName) {
        if (B_GOKARNA_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/gokarna/beaches-in-gokarna/";
        } else if (B_KARWAR_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/karwar/beaches-in-karwar/";
        } else if (B_KAUP_BEACH_UDUPI.equals(placeName)) {
            return "https://www.karnataka.com/udupi/kaup-beach/";
        } else if (B_MALPE_BEACH_UDUPI.equals(placeName)) {
            return "https://www.karnataka.com/udupi/malpe-beach/";
        } else if (B_OM_BEACH_GOKARNA.equals(placeName)) {
            return "https://www.karnataka.com/gokarna/om-beach/";
        } else if (B_SASIHITHLU_BEACH_MANGALORE.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/sasihithlu-beach/";
        } else if (B_PADUBIDRI_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/udupi/padubidri-beach/";
        } else if (H_KUDREMUKH.equals(placeName)) {
            return "https://www.holidify.com/places/kudremukh/";
        } else if (H_MADIKERI.equals(placeName)) {
            return "https://www.holidify.com/places/madikeri/";
        } else if (H_AGUMBE.equals(placeName)) {
            return "https://www.holidify.com/places/shimoga/agumbe-sightseeing-8109.html";
        } else if (H_NANDHI_HILLS.equals(placeName)) {
            return "holidify.com/places/nandi-hills/";
        } else if (H_SHIVAGANGE_HILLS.equals(placeName)) {
            return "https://www.holidify.com/places/shivagange/";
        } else if (H_SAVANADURGA_HILLS.equals(placeName)) {
            return "https://www.holidify.com/places/savandurga/";
        }else if (M_GOL_GUMBAZ.equals(placeName)) {
            return "https://www.yatra.com/indian-monuments/bijapur/gol-gumbaz";
        }else if (M_MYSORE_PALACE.equals(placeName)) {
            return "https://www.mysoretourism.org.in/mysore-maharaja-palace";
        }else if (M_VIDHANA_SOUDHA.equals(placeName)) {
            return "https://www.thrillophilia.com/attractions/vidhana-soudha";
        }else if (M_PHILOMENA_CHURCH.equals(placeName)) {
            return "https://www.mysoretourism.org.in/st-philomenas-church-mysore";
        }else if (M_MENA_BASADI.equals(placeName)) {
            return "https://en.wikipedia.org/wiki/Badami_cave_temples";
        }else if (M_BIDAR_FORT.equals(placeName)) {
            return "https://www.karnatakatourism.org/tour-item/bidar-fort/";
        }
        return "";
    }
}
