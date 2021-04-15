package com.security.travelguide.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.maps.model.LatLng;
import com.security.travelguide.model.PlaceItem;

import java.util.ArrayList;
import java.util.List;

import static com.security.travelguide.helper.UtilityPlaceConstants.B_BHATKAL_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_GOKARNA_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_KARWAR_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_KAUP_BEACH_UDUPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_KURUMGAD_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_MALPE_BEACH_UDUPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_MARAVANTHE_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_MURUDESHWAR_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_OM_BEACH_GOKARNA;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_PADUBIDRI_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_PANAMBUR_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_SASIHITHLU_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_SOMESHWARA_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_SURATHKAL_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_TANNIRBHAVI_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.B_ULLAL_BEACH;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_ALAMATTI_GARDEN;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_CUBBON_PARK;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_DANDELI_NATIONAL_PARK;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_HOSPET_GARDEN;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_LALBAG_BANGALORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.G_MYSORE_ZOO;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_AGUMBE;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_BRAHMAGIRI_HILL;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_CHARMADI_GHAT;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_COORG;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_KODACHADRI;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_KUDREMUKH;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_KUMARA_PARVATHA;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_MADIKERI;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_MULLAYANAGIRI;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_NANDHI_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_RAMANAGARA_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_SAVANADURGA_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_SHARAVATHI_VALLEY;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_SHIVAGANGE_HILLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_SIDDARA_BETTA;
import static com.security.travelguide.helper.UtilityPlaceConstants.H_YANA;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_BANGALORE_PALACE;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_BIDAR_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_BUDDHA_VIHAR;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_CHITRADURGA_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_GOL_GUMBAZ;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_GOMMATESHWARA;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_GULBARGA_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_KITTUR_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_MADIKERI_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_MENA_BASADI;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_MYSORE_PALACE;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_DEVANAHALLI_FORT;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_PHILOMENA_CHURCH;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_SAAVIRA_KAMBADA_BASADI;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_STONE_CHARIOT_HAMPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.M_VIDHANA_SOUDHA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_BADAMI_BANASHANKARI;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_CHAMUNDESHWARI_MYSORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_DARMASTHALA_MANJUNATHA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_GODACHI_VEERABADRA_TEMPLE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_GORAVANAHALLI_MAHALAKSHMI;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_ISKON_BANGALORE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KALASA_TEMPLE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KOLLUR_MOOKAMBIKA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KOTILINGESHWARA_TEMPLE;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KUDALA_SANGAMA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_KUKKE_SUBRAMANYA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_MURUDESHWARA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_SAVADATTI_YELLAMMA;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_SIDDAGANGA_MUTT;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_SRINGERI_SHARDA_PEETHAM;
import static com.security.travelguide.helper.UtilityPlaceConstants.R_SRI_KRISHNA_UDUPI;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_ABBEY_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_ALMATTI_DAM;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_BARACHUKKI_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_BENNE_HOLE_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_CHUNCHANAKATTE_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_GODCHINA_MALKI_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_GOKAK_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_HIDKAL_DAM;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_JOG_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_KALHATTI_GIRI_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_KRS_DAM;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_SATHODI_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_SURAL_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_TUNGABHADRA_DAM;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_UNCHALLI_FALLS;
import static com.security.travelguide.helper.UtilityPlaceConstants.W_VIBHOOTI_FALLS;

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
                        // Set Place Reference Url
                        placeItem.setPlaceReferenceLink(getBeachesReferenceUrls(placeName));
                        // Set Place Location
                        LatLng placeLocation = getBeachesLocationDetails(placeName);
                        placeItem.setPlaceLatitude(placeLocation.latitude);
                        placeItem.setPlaceLongitude(placeLocation.longitude);
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
                        // Set Place Reference Url
                        placeItem.setPlaceReferenceLink(getHillStationsReferenceUrls(placeName));
                        // Set Place Location
                        LatLng placeLocation = getHillStationsLocationDetails(placeName);
                        placeItem.setPlaceLatitude(placeLocation.latitude);
                        placeItem.setPlaceLongitude(placeLocation.longitude);
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
                        // Set Place Reference Url
                        placeItem.setPlaceReferenceLink(getMonumnetsReferenceUrls(placeName));
                        // Set Place Location
                        LatLng placeLocation = getMonumentsLocationDetails(placeName);
                        placeItem.setPlaceLatitude(placeLocation.latitude);
                        placeItem.setPlaceLongitude(placeLocation.longitude);
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
                        // Set Place Reference Url
                        placeItem.setPlaceReferenceLink(getReligiousReferenceUrls(placeName));
                        // Set Place Location
                        LatLng placeLocation = getReligiousLocationDetails(placeName);
                        placeItem.setPlaceLatitude(placeLocation.latitude);
                        placeItem.setPlaceLongitude(placeLocation.longitude);
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
                        // Set Place Reference Url
                        placeItem.setPlaceReferenceLink(getGardenReferenceUrls(placeName));
                        // Set Place Location
                        LatLng placeLocation = getGardensLocationDetails(placeName);
                        placeItem.setPlaceLatitude(placeLocation.latitude);
                        placeItem.setPlaceLongitude(placeLocation.longitude);
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
                        // Set Place Reference Url
                        placeItem.setPlaceReferenceLink(getWaterFallsReferenceUrls(placeName));
                        // Set Place Location
                        LatLng placeLocation = getWaterFallsLocationDetails(placeName);
                        placeItem.setPlaceLatitude(placeLocation.latitude);
                        placeItem.setPlaceLongitude(placeLocation.longitude);
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
        beachList.add(B_SASIHITHLU_BEACH);
        beachList.add(B_PANAMBUR_BEACH);
        beachList.add(B_SOMESHWARA_BEACH);
        beachList.add(B_ULLAL_BEACH);
        beachList.add(B_BHATKAL_BEACH);
        beachList.add(B_MURUDESHWAR_BEACH);
        beachList.add(B_KURUMGAD_BEACH);
        beachList.add(B_SURATHKAL_BEACH);
        beachList.add(B_MARAVANTHE_BEACH);
        beachList.add(B_TANNIRBHAVI_BEACH);
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
        hillStationList.add(H_YANA);
        hillStationList.add(H_SIDDARA_BETTA);
        hillStationList.add(H_COORG);
        hillStationList.add(H_BRAHMAGIRI_HILL);
        hillStationList.add(H_CHARMADI_GHAT);
        hillStationList.add(H_RAMANAGARA_HILLS);
        hillStationList.add(H_SHARAVATHI_VALLEY);
        hillStationList.add(H_MULLAYANAGIRI);
        hillStationList.add(H_KUMARA_PARVATHA);
        hillStationList.add(H_KODACHADRI);
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
        monumentList.add(M_SAAVIRA_KAMBADA_BASADI);
        monumentList.add(M_BANGALORE_PALACE);
        monumentList.add(M_STONE_CHARIOT_HAMPI);
        monumentList.add(M_GOMMATESHWARA);
        monumentList.add(M_CHITRADURGA_FORT);
        monumentList.add(M_BUDDHA_VIHAR);
        monumentList.add(M_KITTUR_FORT);
        monumentList.add(M_MADIKERI_FORT);
        monumentList.add(M_GULBARGA_FORT);
        monumentList.add(M_DEVANAHALLI_FORT);
        return monumentList;
    }

    private static List<String> getReligiousList() {
        // Religious Names
        List<String> religiousList = new ArrayList<>();
        religiousList.add(R_KUDALA_SANGAMA);
        religiousList.add(R_ISKON_BANGALORE);
        religiousList.add(R_MURUDESHWARA);
        religiousList.add(R_SRI_KRISHNA_UDUPI);
        religiousList.add(R_DARMASTHALA_MANJUNATHA);
        religiousList.add(R_KUKKE_SUBRAMANYA);
        religiousList.add(R_CHAMUNDESHWARI_MYSORE);
        religiousList.add(R_KOTILINGESHWARA_TEMPLE);
        religiousList.add(R_SRINGERI_SHARDA_PEETHAM);
        religiousList.add(R_KALASA_TEMPLE);
        religiousList.add(R_KOLLUR_MOOKAMBIKA);
        religiousList.add(R_GORAVANAHALLI_MAHALAKSHMI);
        religiousList.add(R_SIDDAGANGA_MUTT);
        religiousList.add(R_BADAMI_BANASHANKARI);
        religiousList.add(R_GODACHI_VEERABADRA_TEMPLE);
        religiousList.add(R_SAVADATTI_YELLAMMA);
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
        gardensList.add(G_DANDELI_NATIONAL_PARK);
        return gardensList;
    }

    private static List<String> getWaterFallsList() {
        // Water Falls Names
        List<String> waterFallsList = new ArrayList<>();
        waterFallsList.add(W_JOG_FALLS);
        waterFallsList.add(W_GOKAK_FALLS);
        waterFallsList.add(W_GODCHINA_MALKI_FALLS);
        waterFallsList.add(W_ABBEY_FALLS);
        waterFallsList.add(W_BARACHUKKI_FALLS);
        waterFallsList.add(W_BENNE_HOLE_FALLS);
        waterFallsList.add(W_CHUNCHANAKATTE_FALLS);
        waterFallsList.add(W_KALHATTI_GIRI_FALLS);
        waterFallsList.add(W_SATHODI_FALLS);
        waterFallsList.add(W_SURAL_FALLS);
        waterFallsList.add(W_VIBHOOTI_FALLS);
        waterFallsList.add(W_UNCHALLI_FALLS);
        waterFallsList.add(W_ALMATTI_DAM);
        waterFallsList.add(W_TUNGABHADRA_DAM);
        waterFallsList.add(W_KRS_DAM);
        waterFallsList.add(W_HIDKAL_DAM);
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


    // Reference Links Functions
    public static String getBeachesReferenceUrls(String placeName) {
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
        } else if (B_SASIHITHLU_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/sasihithlu-beach/";
        } else if (B_PADUBIDRI_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/udupi/padubidri-beach/";
        }else if (B_PANAMBUR_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/panambur-beach/";
        }else if (B_SOMESHWARA_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/someshwara-beach/";
        }else if (B_ULLAL_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/ullal-beach/";
        }else if (B_BHATKAL_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/murdeshwar/bhatkal-beach/";
        }else if (B_MURUDESHWAR_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/murdeshwar/murdeshwar-beach/";
        }else if (B_KURUMGAD_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/karwar/kurumgad-beach/";
        }else if (B_SURATHKAL_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/surathkal/surathkal-beach/";
        }else if (B_MARAVANTHE_BEACH.equals(placeName)) {
            return "https://www.karnataka.com/maravanthe/maravanthe-beach/";
        }else if (B_TANNIRBHAVI_BEACH.equals(placeName)) {
            return "https://www.thrillophilia.com/attractions/tannirbhavi-beach";
        }
        return "";
    }

    public static String getHillStationsReferenceUrls(String placeName) {
        if (H_KUDREMUKH.equals(placeName)) {
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
        }else if (H_YANA.equals(placeName)) {
            return "https://www.karnataka.com/hubli/yana-hill-station/";
        }else if (H_SIDDARA_BETTA.equals(placeName)) {
            return "https://www.karnataka.com/madhugiri/siddara-betta/";
        }else if (H_COORG.equals(placeName)) {
            return "https://www.karnataka.com/coorg/about-coorg/";
        }else if (H_BRAHMAGIRI_HILL.equals(placeName)) {
            return "https://www.karnataka.com/coorg/brahmagiri-trek/";
        }else if (H_CHARMADI_GHAT.equals(placeName)) {
            return "https://www.karnataka.com/chikmagalur/charmadi-ghat-trek/";
        }else if (H_RAMANAGARA_HILLS.equals(placeName)) {
            return "https://www.karnataka.com/ramanagara/about-ramanagara/";
        }else if (H_SHARAVATHI_VALLEY.equals(placeName)) {
            return "https://www.karnataka.com/shimoga/sharavathi-valley-trek/";
        }else if (H_MULLAYANAGIRI.equals(placeName)) {
            return "https://www.karnataka.com/chikmagalur/mullayanagiri-trek/";
        }else if (H_KUMARA_PARVATHA.equals(placeName)) {
            return "https://www.karnataka.com/coorg/kumara-parvatha-trek/";
        }else if (H_KODACHADRI.equals(placeName)) {
            return "https://www.karnataka.com/kollur/kodachadri-trek/";
        }
        return "";
    }

    public static String getMonumnetsReferenceUrls(String placeName) {
        if (M_GOL_GUMBAZ.equals(placeName)) {
            return "https://www.yatra.com/indian-monuments/bijapur/gol-gumbaz";
        } else if (M_MYSORE_PALACE.equals(placeName)) {
            return "https://www.mysoretourism.org.in/mysore-maharaja-palace";
        } else if (M_VIDHANA_SOUDHA.equals(placeName)) {
            return "https://www.thrillophilia.com/attractions/vidhana-soudha";
        } else if (M_PHILOMENA_CHURCH.equals(placeName)) {
            return "https://www.mysoretourism.org.in/st-philomenas-church-mysore";
        } else if (M_MENA_BASADI.equals(placeName)) {
            return "https://en.wikipedia.org/wiki/Badami_cave_temples";
        } else if (M_BIDAR_FORT.equals(placeName)) {
            return "https://www.karnatakatourism.org/tour-item/bidar-fort/";
        }else if (M_SAAVIRA_KAMBADA_BASADI.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/saavira-kambada-basadi-moodbidri/";
        }else if (M_BANGALORE_PALACE.equals(placeName)) {
            return "https://www.karnataka.com/bangalore/bangalore-palace/";
        }else if (M_STONE_CHARIOT_HAMPI.equals(placeName)) {
            return "https://www.karnataka.com/hampi/stone-chariot/";
        }else if (M_GOMMATESHWARA.equals(placeName)) {
            return "https://www.karnataka.com/personalities/saint-bahubali/";
        }else if (M_CHITRADURGA_FORT.equals(placeName)) {
            return "https://www.karnataka.com/chitradurga/about-chitradurga/";
        }else if (M_BUDDHA_VIHAR.equals(placeName)) {
            return "https://www.karnataka.com/gulbarga/buddha-vihara/";
        }else if (M_KITTUR_FORT.equals(placeName)) {
            return "https://www.karnataka.com/belgaum/kittur-fort-and-palace/";
        }else if (M_MADIKERI_FORT.equals(placeName)) {
            return "https://www.karnataka.com/coorg/madikeri-fort/";
        }else if (M_GULBARGA_FORT.equals(placeName)) {
            return "https://www.karnataka.com/gulbarga/gulbarga-fort/";
        }else if (M_DEVANAHALLI_FORT.equals(placeName)) {
            return "https://www.karnataka.com/bangalore/devanahalli-fort/";
        }
        return "";
    }

    public static String getReligiousReferenceUrls(String placeName) {
        if (R_KUDALA_SANGAMA.equals(placeName)) {
            return "https://travel2karnataka.com/kudala_sangama.htm";
        } else if (R_ISKON_BANGALORE.equals(placeName)) {
            return "https://www.thrillophilia.com/attractions/iskcon-temple-bangalore";
        } else if (R_MURUDESHWARA.equals(placeName)) {
            return "https://www.thrillophilia.com/cities/murudeshwara";
        } else if (R_SRI_KRISHNA_UDUPI.equals(placeName)) {
            return "https://www.thrillophilia.com/cities/udupi";
        } else if (R_DARMASTHALA_MANJUNATHA.equals(placeName)) {
            return "https://www.shridharmasthala.org/about-dharmasthala/";
        } else if (R_KUKKE_SUBRAMANYA.equals(placeName)) {
            return "https://www.karnataka.com/mangalore/kukke-subrahmanya-temple/";
        } else if (R_CHAMUNDESHWARI_MYSORE.equals(placeName)) {
            return "https://www.karnataka.com/mysore/chamundi-hills/";
        } else if (R_KOTILINGESHWARA_TEMPLE.equals(placeName)) {
            return "https://www.karnataka.com/kolar/kotilingeshwara-temple/";
        } else if (R_SRINGERI_SHARDA_PEETHAM.equals(placeName)) {
            return "https://www.karnataka.com/sringeri/about-sringeri/";
        }else if (R_KALASA_TEMPLE.equals(placeName)) {
            return "https://www.karnataka.com/chikmagalur/kalasa/";
        }else if (R_KOLLUR_MOOKAMBIKA.equals(placeName)) {
            return "https://www.karnataka.com/kollur/about-kollur/";
        }else if (R_GORAVANAHALLI_MAHALAKSHMI.equals(placeName)) {
            return "https://www.karnataka.com/tumkur/mahalakshmi-temple-goravanahalli/";
        }else if (R_SIDDAGANGA_MUTT.equals(placeName)) {
            return "https://www.karnataka.com/tumkur/siddaganga-mutt/";
        }else if (R_BADAMI_BANASHANKARI.equals(placeName)) {
            return "https://www.karnataka.com/badami/cholachagudd-banashankari-amma-temple/";
        }else if (R_GODACHI_VEERABADRA_TEMPLE.equals(placeName)) {
            return "https://www.karnataka.com/badami/godachi-veerabhadra-temple/";
        }else if (R_SAVADATTI_YELLAMMA.equals(placeName)) {
            return "https://www.karnataka.com/belgaum/saundatti-yellamma-devi-temple/";
        }
        return "";
    }

    public static String getGardenReferenceUrls(String placeName) {
        if (G_ALAMATTI_GARDEN.equals(placeName)) {
            return "https://www.karnatakatourism.org/tour-item/lav-kush-garden-almatti/";
        } else if (G_HOSPET_GARDEN.equals(placeName)) {
            return "https://www.hosapeteonline.in/city-guide/wildlife-and-parks-in-hospet";
        } else if (G_LALBAG_BANGALORE.equals(placeName)) {
            return "https://www.thrillophilia.com/attractions/lal-bagh-botanical-garden";
        } else if (G_CUBBON_PARK.equals(placeName)) {
            return "https://www.thrillophilia.com/attractions/cubbon-park";
        } else if (G_MYSORE_ZOO.equals(placeName)) {
            return "https://www.karnataka.com/mysore/mysore-zoo/";
        } else if (G_DANDELI_NATIONAL_PARK.equals(placeName)) {
            return "https://www.tourmyindia.com/wildlife_sancturies/dandeli-national-park.html";
        }
        return "";
    }

    public static String getWaterFallsReferenceUrls(String placeName) {
        if (W_JOG_FALLS.equals(placeName)) {
            return "https://www.tourism-of-india.com/jog-falls-tour.html";
        } else if (W_GOKAK_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/belgaum/gokak-falls/";
        } else if (W_GODCHINA_MALKI_FALLS.equals(placeName)) {
            return "https://www.trawel.co.in/city/Belagavi/godchinamalaki-falls";
        } else if (W_ABBEY_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/coorg/abbey-falls/";
        } else if (W_BARACHUKKI_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/mandya/barachukki-gaganachukki-falls-shivanasamudra/";
        } else if (W_BENNE_HOLE_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/karwar/bennehole-waterfalls/";
        } else if (W_CHUNCHANAKATTE_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/mysore/chunchanakatte-falls/";
        } else if (W_KALHATTI_GIRI_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/chikmagalur/kalhatti-giri-falls/";
        } else if (W_SATHODI_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/hubli/sathodi-falls/";
        } else if (W_SURAL_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/belgaum/sural-falls/";
        } else if (W_VIBHOOTI_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/karwar/vibhooti-falls/";
        } else if (W_UNCHALLI_FALLS.equals(placeName)) {
            return "https://www.karnataka.com/shimoga/unchalli-falls/";
        } else if (W_ALMATTI_DAM.equals(placeName)) {
            return "https://www.karnataka.com/bijapur/almatti-dam/";
        } else if (W_TUNGABHADRA_DAM.equals(placeName)) {
            return "https://explorebees.com/India/KARNATAKA/Hampi/Tungbhadra+Dam+And+Garden/pl50281";
        } else if (W_KRS_DAM.equals(placeName)) {
            return "https://travel.earth/krs-dam-guide-visvesvarayas-legacy/";
        } else if (W_HIDKAL_DAM.equals(placeName)) {
            return "https://en.wikipedia.org/wiki/Raja_Lakhamagouda_dam";
        }
        return "";
    }

    // Specific Places LatLong Functions

    public static LatLng getBeachesLocationDetails(String placeName) {
        if (B_GOKARNA_BEACH.equals(placeName)) {
            return new LatLng(14.5432218, 74.3140062);
        } else if (B_KARWAR_BEACH.equals(placeName)) {
            return new LatLng(14.8311008, 74.1250158);
        } else if (B_KAUP_BEACH_UDUPI.equals(placeName)) {
            return new LatLng(13.2252087, 74.7369294);
        } else if (B_MALPE_BEACH_UDUPI.equals(placeName)) {
            return new LatLng(13.3612261, 74.6977701);
        } else if (B_OM_BEACH_GOKARNA.equals(placeName)) {
            return new LatLng(14.5192901, 74.3194347);
        } else if (B_SASIHITHLU_BEACH.equals(placeName)) {
            return new LatLng(13.0703809, 74.7775755);
        } else if (B_PADUBIDRI_BEACH.equals(placeName)) {
            return new LatLng(13.1308061, 74.762498);
        }else if (B_PANAMBUR_BEACH.equals(placeName)) {
            return new LatLng(12.936954, 74.804623);
        }else if (B_SOMESHWARA_BEACH.equals(placeName)) {
            return new LatLng(12.786331, 74.853695);
        }else if (B_ULLAL_BEACH.equals(placeName)) {
            return new LatLng(12.807656, 74.842757);
        }else if (B_BHATKAL_BEACH.equals(placeName)) {
            return new LatLng(13.992258, 74.514458);
        }else if (B_MURUDESHWAR_BEACH.equals(placeName)) {
            return new LatLng(14.095227, 74.485656);
        }else if (B_KURUMGAD_BEACH.equals(placeName)) {
            return new LatLng(14.847758, 74.115604);
        }else if (B_SURATHKAL_BEACH.equals(placeName)) {
            return new LatLng(13.0096015,74.7886857);
        }else if (B_MARAVANTHE_BEACH.equals(placeName)) {
            return new LatLng(13.704708, 74.642438);
        }else if (B_TANNIRBHAVI_BEACH.equals(placeName)) {
            return new LatLng(12.886762, 74.815224);
        }
        return new LatLng(0.0, 0.0);
    }

    public static LatLng getHillStationsLocationDetails(String placeName) {
        if (H_KUDREMUKH.equals(placeName)) {
            return new LatLng(13.2231912, 75.2510675);
        } else if (H_MADIKERI.equals(placeName)) {
            return new LatLng(12.429940, 75.722328);
        } else if (H_AGUMBE.equals(placeName)) {
            return new LatLng(13.5064449, 75.075116);
        } else if (H_NANDHI_HILLS.equals(placeName)) {
            return new LatLng(13.383078, 77.6674479);
        } else if (H_SHIVAGANGE_HILLS.equals(placeName)) {
            return new LatLng(13.1697204, 77.2206819);
        } else if (H_SAVANADURGA_HILLS.equals(placeName)) {
            return new LatLng(12.899733, 77.2656555);
        }else if (H_YANA.equals(placeName)) {
            return new LatLng(14.590163, 74.566667);
        }else if (H_SIDDARA_BETTA.equals(placeName)) {
            return new LatLng(13.565131, 77.142188);
        }else if (H_COORG.equals(placeName)) {
            return new LatLng(12.420010, 75.729191);
        }else if (H_BRAHMAGIRI_HILL.equals(placeName)) {
            return new LatLng(12.424417, 75.738197);
        }else if (H_CHARMADI_GHAT.equals(placeName)) {
            return new LatLng(13.058180, 75.429480);
        }else if (H_RAMANAGARA_HILLS.equals(placeName)) {
            return new LatLng(12.7529302,77.3019236);
        }else if (H_SHARAVATHI_VALLEY.equals(placeName)) {
            return new LatLng(14.2790231,74.4749231);
        }else if (H_MULLAYANAGIRI.equals(placeName)) {
            return new LatLng(13.390861, 75.721314);
        }else if (H_KUMARA_PARVATHA.equals(placeName)) {
            return new LatLng(12.662849, 75.687289);
        }else if (H_KODACHADRI.equals(placeName)) {
            return new LatLng(13.8563942,74.8703597);
        }
        return new LatLng(0.0, 0.0);
    }

    public static LatLng getMonumentsLocationDetails(String placeName) {
        if (M_GOL_GUMBAZ.equals(placeName)) {
            return new LatLng(16.830174, 75.736117);
        } else if (M_MYSORE_PALACE.equals(placeName)) {
            return new LatLng(12.305153, 76.655179);
        } else if (M_VIDHANA_SOUDHA.equals(placeName)) {
            return new LatLng(12.9791947, 77.5913193);
        } else if (M_PHILOMENA_CHURCH.equals(placeName)) {
            return new LatLng(12.321024, 76.658254);
        } else if (M_MENA_BASADI.equals(placeName)) {
            return new LatLng(15.916642, 75.691002);
        } else if (M_BIDAR_FORT.equals(placeName)) {
            return new LatLng(17.922706, 77.526042);
        }else if (M_SAAVIRA_KAMBADA_BASADI.equals(placeName)) {
            return new LatLng(13.074266, 74.998174);
        }else if (M_BANGALORE_PALACE.equals(placeName)) {
            return new LatLng(12.9987041,77.5919296);
        }else if (M_STONE_CHARIOT_HAMPI.equals(placeName)) {
            return new LatLng(15.342267, 76.475786);
        }else if (M_GOMMATESHWARA.equals(placeName)) {
            return new LatLng(12.853962, 76.484659);
        }else if (M_CHITRADURGA_FORT.equals(placeName)) {
            return new LatLng(14.216044, 76.398915);
        }else if (M_BUDDHA_VIHAR.equals(placeName)) {
            return new LatLng(17.306700, 76.892529);
        }else if (M_KITTUR_FORT.equals(placeName)) {
            return new LatLng(15.601225, 74.791409);
        }else if (M_MADIKERI_FORT.equals(placeName)) {
            return new LatLng(12.421042, 75.738677);
        }else if (M_GULBARGA_FORT.equals(placeName)) {
            return new LatLng(17.3404462,76.8296551);
        }else if (M_DEVANAHALLI_FORT.equals(placeName)) {
            return new LatLng(13.249751, 77.708188);
        }
        return new LatLng(0.0, 0.0);
    }

    public static LatLng getReligiousLocationDetails(String placeName) {
        if (R_KUDALA_SANGAMA.equals(placeName)) {
            return new LatLng(16.205774, 76.059213);
        } else if (R_ISKON_BANGALORE.equals(placeName)) {
            return new LatLng(13.0097206, 77.550799);
        } else if (R_MURUDESHWARA.equals(placeName)) {
            return new LatLng(14.093609, 74.483835);
        } else if (R_SRI_KRISHNA_UDUPI.equals(placeName)) {
            return new LatLng(13.3411359, 74.752003);
        } else if (R_DARMASTHALA_MANJUNATHA.equals(placeName)) {
            return new LatLng(12.950344, 75.380587);
        } else if (R_KUKKE_SUBRAMANYA.equals(placeName)) {
            return new LatLng(12.675203, 75.605845);
        } else if (R_CHAMUNDESHWARI_MYSORE.equals(placeName)) {
            return new LatLng(12.272465, 76.670738);
        } else if (R_KOTILINGESHWARA_TEMPLE.equals(placeName)) {
            return new LatLng(12.995150, 78.295711);
        } else if (R_SRINGERI_SHARDA_PEETHAM.equals(placeName)) {
            return new LatLng(13.4162243, 75.2520464);
        }else if (R_KALASA_TEMPLE.equals(placeName)) {
            return new LatLng(13.232640, 75.363374);
        }else if (R_KOLLUR_MOOKAMBIKA.equals(placeName)) {
            return new LatLng(13.863827, 74.814464);
        }else if (R_GORAVANAHALLI_MAHALAKSHMI.equals(placeName)) {
            return new LatLng(13.472025, 77.282746);
        }else if (R_SIDDAGANGA_MUTT.equals(placeName)) {
            return new LatLng(13.321826, 77.148378);
        }else if (R_BADAMI_BANASHANKARI.equals(placeName)) {
            return new LatLng(13.321826, 77.148378);
        }else if (R_GODACHI_VEERABADRA_TEMPLE.equals(placeName)) {
            return new LatLng(16.0134923,75.1941104);
        }else if (R_SAVADATTI_YELLAMMA.equals(placeName)) {
            return new LatLng(15.754554, 75.154191);
        }
        return new LatLng(0.0, 0.0);
    }

    public static LatLng getGardensLocationDetails(String placeName) {
        if (G_ALAMATTI_GARDEN.equals(placeName)) {
            return new LatLng(16.339343, 75.896210);
        } else if (G_HOSPET_GARDEN.equals(placeName)) {
            return new LatLng(15.268835, 76.325420);
        } else if (G_LALBAG_BANGALORE.equals(placeName)) {
            return new LatLng(12.948661, 77.582338);
        } else if (G_CUBBON_PARK.equals(placeName)) {
            return new LatLng(12.976677, 77.593873);
        } else if (G_MYSORE_ZOO.equals(placeName)) {
            return new LatLng(12.302315, 76.665788);
        } else if (G_DANDELI_NATIONAL_PARK.equals(placeName)) {
            return new LatLng(15.246803, 74.622078);
        }
        return new LatLng(0.0, 0.0);
    }

    public static LatLng getWaterFallsLocationDetails(String placeName) {
        if (W_JOG_FALLS.equals(placeName)) {
            return new LatLng(14.231168, 74.818760);
        } else if (W_GOKAK_FALLS.equals(placeName)) {
            return new LatLng(16.191931, 74.776926);
        } else if (W_GODCHINA_MALKI_FALLS.equals(placeName)) {
            return new LatLng(16.123734, 74.745203);
        } else if (W_ABBEY_FALLS.equals(placeName)) {
            return new LatLng(12.458054, 75.716642);
        } else if (W_BARACHUKKI_FALLS.equals(placeName)) {
            return new LatLng(12.287821, 77.183671);
        } else if (W_BENNE_HOLE_FALLS.equals(placeName)) {
            return new LatLng(14.5044923, 74.6244319);
        } else if (W_CHUNCHANAKATTE_FALLS.equals(placeName)) {
            return new LatLng(12.505904, 76.295861);
        } else if (W_KALHATTI_GIRI_FALLS.equals(placeName)) {
            return new LatLng(13.536311, 75.782068);
        } else if (W_SATHODI_FALLS.equals(placeName)) {
            return new LatLng(14.949714, 74.585082);
        } else if (W_SURAL_FALLS.equals(placeName)) {
            return new LatLng(15.674933, 74.185989);
        } else if (W_VIBHOOTI_FALLS.equals(placeName)) {
            return new LatLng(14.598101, 74.551185);
        } else if (W_UNCHALLI_FALLS.equals(placeName)) {
            return new LatLng(14.409061, 74.747428);
        } else if (W_ALMATTI_DAM.equals(placeName)) {
            return new LatLng(16.330978, 75.887052);
        } else if (W_TUNGABHADRA_DAM.equals(placeName)) {
            return new LatLng(15.268835, 76.325420);
        } else if (W_KRS_DAM.equals(placeName)) {
            return new LatLng(12.425459, 76.572409);
        } else if (W_HIDKAL_DAM.equals(placeName)) {
            return new LatLng(16.143003, 74.642841);
        }
        return new LatLng(0.0, 0.0);
    }
}
