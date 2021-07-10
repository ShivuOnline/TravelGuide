package com.security.travelguide.views.updateMpin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.FireBaseDatabaseConstants;
import com.security.travelguide.helper.NetworkUtil;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.helper.UtilityConstants;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.userDetails.UserMain;
import com.security.travelguide.views.main.MainActivity;
import com.security.travelguide.views.signup.SignupActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateMPin extends Fragment {
    private static final String TAG = "UpdateMPin";
    private View rootView;
    private CircleImageView userProfilePic;

    private TextInputEditText editOldMPin, editNewMPin;
    private TextView textPersonName, textMobileNumber;
    private Button btnUpdate;
    private ProgressDialog progressDialog;

    // Firebase Storage
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mUserReference;

    private TextView textTitle;


    public UpdateMPin() {
        // Required empty public constructor
    }


    public static UpdateMPin createInstance(Bundle bundle) {
        UpdateMPin fragment = new UpdateMPin();
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
        rootView = inflater.inflate(R.layout.fragment_update_mpin, container, false);
        textTitle = requireActivity().findViewById(R.id.title_header);
        if (textTitle != null) {
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(AppConstants.SETTINGS_UPDATE_MPIN);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            ((MainActivity) requireActivity()).setTitle("Update mPin");

            progressDialog = new ProgressDialog(requireContext());

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
            userProfilePic = rootView.findViewById(R.id.image_user);

            editOldMPin = rootView.findViewById(R.id.edit_old_mPin);
            editNewMPin = rootView.findViewById(R.id.edit_new_mPin);

            textPersonName = rootView.findViewById(R.id.text_personal_name);
            textMobileNumber = rootView.findViewById(R.id.text_mobile_number_value);

            btnUpdate = rootView.findViewById(R.id.btn_update);

            editOldMPin.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
            editNewMPin.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (NetworkUtil.getConnectivityStatus(requireContext())) {
                        if (validateFields()) {
                            UserMain loginUser = UserUtils.getLoginUserDetails(requireContext());
                            Log.d(TAG, "onClick: loginUser:" + loginUser);
                            if (loginUser.getmPin().equals(editOldMPin.getText().toString().trim())) {
                                loginUser.setmPin(editNewMPin.getText().toString().trim());
                                showProgressDialog("Processing please wait.");
                                Log.d(TAG, "onClick: loginUser:" + loginUser);
                                updateUserDetails(loginUser);
                            } else {
                                TravelGuideToast.showErrorToastWithBottom(requireContext(), "Please verify old mPin.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
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

    private void clearFields() {
        try {
            editOldMPin.setText("");
            editNewMPin.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        try {
            if (UserUtils.isEmptyField(editOldMPin.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "Please enter old mPin.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (editOldMPin.getText().toString().trim().length() < 4) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "Old mPin must be 4 Digits.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (UserUtils.isEmptyField(editNewMPin.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "Please enter new mPin.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (editNewMPin.getText().toString().trim().length() < 4) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "New mPin must be 4 Digits.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (editOldMPin.getText().toString().trim().equals(editNewMPin.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(requireContext(), "New mPin not same as Old mPin", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
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
                            Log.d(TAG, "onSuccess: " + UserUtils.getLoginUserDetails(requireContext()));
                            TravelGuideToast.showSuccessToastWithBottom(requireContext(), "mPin updated successfully", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                            updateUserPicFromUrl(userMain);
                            clearFields();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideProgressDialog();
                            TravelGuideToast.showErrorToastWithBottom(requireContext(), "Failed to update mPin", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
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
                textPersonName.setText(userMain.getUserName());
                textMobileNumber.setText(userMain.getMobileNumber());
                updateUserPicFromUrl(userMain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserPicFromUrl(UserMain user) {
        try {

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