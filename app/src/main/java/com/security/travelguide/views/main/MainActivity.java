package com.security.travelguide.views.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.NetworkUtil;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.userDetails.UserMain;
import com.security.travelguide.views.beaches.Beaches;
import com.security.travelguide.views.dashboard.Dashboard;
import com.security.travelguide.views.gallery.UserGallery;
import com.security.travelguide.views.gardens.Gardens;
import com.security.travelguide.views.hillstations.HillStations;
import com.security.travelguide.views.login.LoginActivity;
import com.security.travelguide.views.monuments.Monuments;
import com.security.travelguide.views.photoupload.PhotoUpload;
import com.security.travelguide.views.profile.Profile;
import com.security.travelguide.views.religious.Religious;
import com.security.travelguide.views.settings.Settings;
import com.security.travelguide.views.updateMpin.UpdateMPin;
import com.security.travelguide.views.waterfalls.WaterFalls;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity implements Dashboard.OnFragmentInteractionListener, Beaches.OnFragmentInteractionListener,
        Gardens.OnFragmentInteractionListener, HillStations.OnFragmentInteractionListener, Monuments.OnFragmentInteractionListener,
        Religious.OnFragmentInteractionListener, WaterFalls.OnFragmentInteractionListener, Settings.OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    public static BottomNavigation bottomNavigationView;
    private TextView titleHeader;
    private CircleImageView userProfilePic;
    private ImageView imageLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);

            titleHeader = findViewById(R.id.title_header);
            userProfilePic = findViewById(R.id.profile_pic);
            imageLogout = findViewById(R.id.logout);

            fragmentManager = getSupportFragmentManager();

            bottomNavigationView = findViewById(R.id.bottom_navigation);

            bottomNavigationView.setMenuItemSelectionListener(new BottomNavigation.OnMenuItemSelectionListener() {
                @Override
                public void onMenuItemSelect(int id, int position, boolean b) {
                    switch (position) {
                        case 0:
                            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(), Integer.toString(getFragmentCount())).commit();
                            break;
                        case 1:
                            if (checkInternet()) {
                                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new PhotoUpload(), Integer.toString(getFragmentCount())).commit();
                            }
                            break;
                        case 2:
                            if (checkInternet()) {
                                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new UserGallery(), Integer.toString(getFragmentCount())).commit();
                            }
                            break;
                        case 3:
                            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Settings(), Integer.toString(getFragmentCount())).commit();
                            break;
                    }
                }

                @Override
                public void onMenuItemReselect(int id, int position, boolean b) {
                    switch (position) {
                        case 0:
                            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(), Integer.toString(getFragmentCount())).commit();
                            break;
                        case 1:
                            if (checkInternet()) {
                                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new PhotoUpload(), Integer.toString(getFragmentCount())).commit();
                            }
                            break;
                        case 2:
                            if (checkInternet()) {
                                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new UserGallery(), Integer.toString(getFragmentCount())).commit();
                            }
                            break;
                        case 3:
                            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Settings(), Integer.toString(getFragmentCount())).commit();
                            break;
                    }
                }
            });

            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard()).commit();

            imageLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialogForLogout();
                }
            });

            loadProfilePic();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkInternet() {
        try {
            if (NetworkUtil.getConnectivityStatus(MainActivity.this)) {
                return true;
            } else {
                TravelGuideToast.showErrorToast(MainActivity.this, getString(R.string.no_internet), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void loadProfilePic() {
        try {
            UserMain loginUser = UserUtils.getLoginUserDetails(MainActivity.this);
            if (loginUser != null) {
                if (loginUser.getProfilePicUrl() != null) {
                    Glide.with(userProfilePic)
                            .load(loginUser.getProfilePicUrl())
                            .fitCenter()
                            .centerCrop()
                            .into(userProfilePic);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlertDialogForLogout() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_with_two_buttons, null);
            builder.setView(dialogView);
            builder.setCancelable(false);

            // TextView and EditText Initialization
            TextView textAlertHeader = dialogView.findViewById(R.id.dialog_message_header);
            TextView textAlertDesc = dialogView.findViewById(R.id.dialog_message_desc);

            TextView textBtnNo = dialogView.findViewById(R.id.text_button_left);
            TextView textBtnYes = dialogView.findViewById(R.id.text_button_right);

            textAlertHeader.setText("Alert..!");
            String logoutSureMessage = "Are you sure want to logout from app?";

            textAlertDesc.setText(logoutSureMessage);
            textBtnNo.setText("No");
            textBtnYes.setText("Yes");

            AlertDialog alert = builder.create();
            alert.show();

            textBtnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        alert.dismiss();
                        logout();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            textBtnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        alert.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            UserUtils.removeAllDataWhenLogout(MainActivity.this);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideBottomNav() {
        try {
            if (bottomNavigationView != null) {
                bottomNavigationView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBottomNav() {
        try {
            if (bottomNavigationView != null) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBottomNavigationPosition(int position) {
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedIndex(position, true);
        }
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        try {
            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, fragment, Integer.toString(getFragmentCount())).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getFragmentCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    @Override
    public void onBackPressed() {
        try {
            Log.d(TAG, "onBackPressed: ");
            fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.frame_layout_main);
            if (fragment instanceof Beaches) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof HillStations) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Monuments) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Religious) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Gardens) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof WaterFalls) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof PhotoUpload) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
                setBottomNavigationPosition(0);
            } else if (fragment instanceof UserGallery) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
                setBottomNavigationPosition(0);
            } else if (fragment instanceof Settings) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
                setBottomNavigationPosition(0);
            } else if (fragment instanceof Profile) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Settings(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof UpdateMPin) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Settings(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Dashboard) {
                super.onBackPressed();
            } else if (getFragmentCount() == 0) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == Activity.RESULT_OK) {
                switch (requestCode) {
                    case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                        Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frame_layout_main)).onActivityResult(requestCode, resultCode, data);
                        break;
                    case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                        Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frame_layout_main)).onActivityResult(requestCode, resultCode, data);
                        break;
                    default:
                        Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frame_layout_main)).onActivityResult(requestCode, resultCode, data);
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}