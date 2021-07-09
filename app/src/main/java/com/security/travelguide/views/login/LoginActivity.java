package com.security.travelguide.views.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.security.travelguide.BuildConfig;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.FireBaseDatabaseConstants;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.userDetails.UserMain;
import com.security.travelguide.views.main.MainActivity;
import com.security.travelguide.views.signup.SignupActivity;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int SIGNUP_REQUEST_CODE = 101;
    private ProgressDialog progressDialog;

    private EditText editMobileNumber;
    private TextInputEditText editMPin;
    private TextView textSignup, textVersion;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            progressDialog = new ProgressDialog(LoginActivity.this);

            setContentView(R.layout.activity_login);

            setUpView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpView() {
        try {
            editMobileNumber = findViewById(R.id.edit_mobile_number);
            editMPin = findViewById(R.id.edit_mPin);

            textSignup = findViewById(R.id.text_signup);
            textVersion = findViewById(R.id.text_version);

            btnLogin = findViewById(R.id.btn_login);

            editMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
            editMPin.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

            String version = "Version." + BuildConfig.VERSION_NAME;
            textVersion.setText(version);

            SpannableString signUpStyledString = new SpannableString(getApplicationContext().getResources().getString(R.string.account_signup));
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivityForResult(intent, SIGNUP_REQUEST_CODE);
                }
            };
            signUpStyledString.setSpan(clickableSpan, 23, 30, 0);
            signUpStyledString.setSpan(new RelativeSizeSpan(1.2f), 23, 30, 0);
            signUpStyledString.setSpan(new ForegroundColorSpan(Color.BLACK), 23, 30, 0);
            textSignup.setText(signUpStyledString);
            textSignup.setMovementMethod(LinkMovementMethod.getInstance());

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validateFields()) {
                        Log.d(TAG, "onClick mobileNumber: " + editMobileNumber.getText().toString().trim());
                        Log.d(TAG, "onClick mPin: " + editMPin.getText().toString().trim());

                        String userMobileNumber = editMobileNumber.getText().toString().trim();
                        String userMPin = editMPin.getText().toString().trim();

                        showProgressDialog("Verifying please wait.");

                        verifyUserLogin(LoginActivity.this, userMobileNumber, userMPin);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        try {
            if (editMobileNumber.getText().toString().trim().isEmpty()) {
                TravelGuideToast.showErrorToastWithBottom(LoginActivity.this, "Please enter mobile number", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (editMPin.getText().toString().trim().isEmpty()) {
                TravelGuideToast.showErrorToastWithBottom(LoginActivity.this, "Please enter mPin", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (editMPin.getText().toString().trim().length() > 4 || editMPin.getText().toString().trim().length() < 4) {
                TravelGuideToast.showErrorToastWithBottom(LoginActivity.this, "mPin must be four digits", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void navigateToMainActivity() {
        try {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyUserLogin(Context context, String userMobileNumber, String mPin) {
        try {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FireBaseDatabaseConstants.USERS_TABLE);

            databaseReference.child(userMobileNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String mobileNumber = snapshot.child(FireBaseDatabaseConstants.MOBILE_NUMBER).getValue(String.class);
                        String mobilePin = snapshot.child(FireBaseDatabaseConstants.M_PIN).getValue(String.class);
                        Log.d(TAG, "onDataChange: mobileNumber: " + mobileNumber);
                        Log.d(TAG, "onDataChange: mobileNumber: " + mobilePin);
                        if (mobileNumber != null && mobilePin != null) {
                            if (userMobileNumber.equals(mobileNumber) && mobilePin.equals(mPin)) {
                                String mPin = snapshot.child(FireBaseDatabaseConstants.M_PIN).getValue(String.class);
                                String userName = snapshot.child(FireBaseDatabaseConstants.USER_NAME).getValue(String.class);
                                String gender = snapshot.child(FireBaseDatabaseConstants.GENDER).getValue(String.class);
                                String role = snapshot.child(FireBaseDatabaseConstants.ROLE).getValue(String.class);
                                String profilePicUrl = snapshot.child(FireBaseDatabaseConstants.PROFILE_PIC_URL).getValue(String.class);
                                String userActive = snapshot.child(FireBaseDatabaseConstants.IS_ACTIVE).getValue(String.class);


                                if (userActive.equalsIgnoreCase(AppConstants.ACTIVE_USER)) {
                                    UserMain userMain = new UserMain();
                                    userMain.setMobileNumber(mobileNumber);
                                    userMain.setmPin(mPin);
                                    userMain.setUserName(userName);
                                    userMain.setGender(gender);
                                    userMain.setRole(role);
                                    userMain.setIsActive(userActive);
                                    userMain.setProfilePicUrl(profilePicUrl);

                                    UserUtils.saveSharedPrefsString(context, AppConstants.LOGIN_TOKEN, userMain.getMobileNumber());
                                    UserUtils.saveLoginUserDetails(context, userMain);

                                    hideProgressDialog();

                                    TravelGuideToast.showSuccessToastWithBottom(context, "Login success", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                                    navigateToMainActivity();
                                } else {
                                    hideProgressDialog();
                                    TravelGuideToast.showErrorToastWithBottom(context, mobileNumber + " is not activated or deActivated, Please contact admin.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_LONG);
                                }
                            } else {
                                hideProgressDialog();
                                TravelGuideToast.showErrorToastWithBottom(context, "Credential mismatch.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                            }
                        } else {
                            hideProgressDialog();
                            TravelGuideToast.showErrorToastWithBottom(context, "Mobile number and mPin null", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                        }
                    } else {
                        hideProgressDialog();
                        TravelGuideToast.showErrorToastWithBottom(context, "Failed to login, verify credentials", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    hideProgressDialog();
                    TravelGuideToast.showErrorToastWithBottom(context, error.getMessage(), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                    Log.d(TAG, "onCancelled: error: " + error.getMessage());
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            e.printStackTrace();
            TravelGuideToast.showErrorToastWithBottom(context, e.getMessage(), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
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