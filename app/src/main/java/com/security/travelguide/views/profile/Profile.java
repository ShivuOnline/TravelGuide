package com.security.travelguide.views.profile;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jakewharton.rxbinding.view.RxView;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.FireBaseDatabaseConstants;
import com.security.travelguide.helper.NetworkUtil;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.userDetails.UserMain;
import com.security.travelguide.views.main.MainActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Fragment {
    private static final String TAG = "Profile";
    private View rootView;
    private CircleImageView imageUser;

    private EditText editUserName;
    private TextView textGender, textMobileNumber;
    private Button btnUpdate;
    private TextView textTitle;

    private ProgressDialog progressDialog;

    // Firebase Storage
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mUserReference;

    private CircleImageView userProfilePic;


    public Profile() {
        // Required empty public constructor
    }


    public static Profile createInstance(Bundle bundle) {
        Profile fragment = new Profile();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        textTitle = requireActivity().findViewById(R.id.title_header);
        if (textTitle != null) {
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(AppConstants.SETTINGS_PROFILE);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            ((MainActivity) requireActivity()).setTitle("Profile");

            userProfilePic = rootView.findViewById(R.id.image_user);
            progressDialog = new ProgressDialog(requireContext());
            textMobileNumber = rootView.findViewById(R.id.text_mobile_number_value);

            firebaseDatabase = FirebaseDatabase.getInstance();
            mUserReference = FirebaseDatabase.getInstance().getReference(FireBaseDatabaseConstants.USERS_TABLE);

            setUpViews();

            loadProfilePic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        try {
            imageUser = rootView.findViewById(R.id.image_user);

            editUserName = rootView.findViewById(R.id.edit_user_name);

            textGender = rootView.findViewById(R.id.text_gender);

            btnUpdate = rootView.findViewById(R.id.btn_update);

            List<String> genderTypeList = Utils.getGenderType();

            RxView.touches(textGender).subscribe(motionEvent -> {
                try {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(requireContext());
                        builderSingle.setTitle("Select Gender");

                        final ArrayAdapter<String> genderTypeSelectionAdapter = new ArrayAdapter<String>(requireContext(),
                                android.R.layout.select_dialog_singlechoice, genderTypeList) {
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

                        builderSingle.setAdapter(genderTypeSelectionAdapter, (dialog, position) -> {
                            textGender.setText(genderTypeSelectionAdapter.getItem(position));
                        });
                        builderSingle.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (NetworkUtil.getConnectivityStatus(requireContext())) {
                        if (validateFields()) {
                            UserMain loginUser = UserUtils.getLoginUserDetails(requireContext());

                            Log.d(TAG, "onClick: loginUser: " + loginUser);
                            UserMain userMain = new UserMain();
                            userMain.setMobileNumber(loginUser.getMobileNumber());
                            userMain.setmPin(loginUser.getmPin());
                            userMain.setProfilePicUrl(loginUser.getProfilePicUrl());
                            userMain.setRole(loginUser.getProfilePicUrl());
                            userMain.setIsActive(loginUser.getProfilePicUrl());

                            userMain.setUserName(UserUtils.getFieldValue(editUserName));
                            userMain.setGender(textGender.getText().toString().trim());

                            if (isAnyUpdate(loginUser, userMain)) {
                                showProgressDialog("Processing please wait.");
                                updateUserDetails(userMain);
                            } else {
                                TravelGuideToast.showInfoToast(requireContext(), "Nothing to update.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                            }
                        }
                    } else {
                        TravelGuideToast.showErrorToast(requireContext(), getString(R.string.no_internet), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                    }
                }
            });

            UserMain loginUser = UserUtils.getLoginUserDetails(requireContext());
            Log.d(TAG, "setUpViews: loginUser: " + loginUser);
            updateUserDetailsToView(loginUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadProfilePic() {
        try {
            UserMain loginUser = UserUtils.getLoginUserDetails(requireContext());
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

    private boolean isAnyUpdate(UserMain loginUser, UserMain editedUserDetails) {
        try {
            if (loginUser != null) {
                if (!(loginUser.getUserName().equals(editedUserDetails.getUserName()))) {
                    return true;
                } else return !(loginUser.getGender().equals(editedUserDetails.getGender()));
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validateFields() {
        try {
            if (UserUtils.isEmptyField(editUserName.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "Please enter full name.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (UserUtils.isEmptyField(textGender.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "Please select gender.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateUserDetails(UserMain userMain) {
        try {
            mUserReference.child(userMain.getMobileNumber()).setValue(userMain)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            hideProgressDialog();

                            UserUtils.saveLoginUserDetails(requireContext(), userMain);

                            Log.d(TAG, "onSuccess: loginUserUpdate: " + UserUtils.getLoginUserDetails(requireContext()));

                            TravelGuideToast.showSuccessToastWithBottom(requireContext(), "User details updated successfully", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);

                            updateUserDetailsToView(userMain);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideProgressDialog();
                            TravelGuideToast.showErrorToastWithBottom(requireContext(), "Failed to update details", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                        }
                    });
        } catch (Exception e) {
            hideProgressDialog();
            TravelGuideToast.showErrorToastWithBottom(requireContext(), e.getMessage(), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    private void updateUserDetailsToView(UserMain userMain) {
        try {
            if (userMain != null) {
                editUserName.setText(userMain.getUserName());
                textGender.setText(userMain.getGender());
                textMobileNumber.setText(userMain.getMobileNumber());
            }
        } catch (Exception e) {
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
}