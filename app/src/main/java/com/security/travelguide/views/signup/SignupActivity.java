package com.security.travelguide.views.signup;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jakewharton.rxbinding.view.RxView;
import com.security.travelguide.R;
import com.security.travelguide.helper.AppConstants;
import com.security.travelguide.helper.FireBaseDatabaseConstants;
import com.security.travelguide.helper.NetworkUtil;
import com.security.travelguide.helper.UserUtils;
import com.security.travelguide.helper.Utils;
import com.security.travelguide.helper.myTaskToast.TravelGuideToast;
import com.security.travelguide.model.userDetails.UserMain;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private EditText editUserName, editMobileNumber;
    private TextInputEditText mPin;
    private TextView textGender;
    private CircleImageView userProfilePic;
    private Button btnSignup;
    private ProgressDialog progressDialog;

    private long MAX_2_MB = 2000000;

    // Firebase Storage
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mUserReference;

    private Uri profilePicUri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_signup);

            progressDialog = new ProgressDialog(SignupActivity.this);

            firebaseDatabase = FirebaseDatabase.getInstance();
            mUserReference = FirebaseDatabase.getInstance().getReference(FireBaseDatabaseConstants.USERS_TABLE);
            storageReference = FirebaseStorage.getInstance().getReference();
            setUpViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        try {
            editMobileNumber = findViewById(R.id.edit_mobile_phone);
            mPin = findViewById(R.id.edit_mPin);

            userProfilePic = findViewById(R.id.image_user);

            editUserName = findViewById(R.id.edit_name);
            textGender = findViewById(R.id.text_gender);

            btnSignup = findViewById(R.id.btn_signup);

            editMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
            mPin.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

            List<String> genderTypeList = Utils.getGenderType();

            RxView.touches(textGender).subscribe(motionEvent -> {
                try {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SignupActivity.this);
                        builderSingle.setTitle("Select Gender");

                        final ArrayAdapter<String> genderTypeSelectionAdapter = new ArrayAdapter<String>(SignupActivity.this,
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

            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (NetworkUtil.getConnectivityStatus(SignupActivity.this)) {

                        if (validateFields()) {
                            UserMain userMain = new UserMain();
                            userMain.setMobileNumber(UserUtils.getFieldValue(editMobileNumber));
                            userMain.setmPin(UserUtils.getFieldValue(mPin));
                            userMain.setUserName(UserUtils.getFieldValue(editUserName));
                            userMain.setGender(textGender.getText().toString().trim());
                            userMain.setRole(AppConstants.User_ROLE);
                            userMain.setIsActive(AppConstants.ACTIVE_USER);

                            boolean isNotExistUser = verifyUserRegistration(userMain);
                            Log.d(TAG, "onClick: isNotExistUser:" + isNotExistUser);
                            if (isNotExistUser) {
                                long photoSize = getFileSize(profilePicUri);
                                Log.d(TAG, "onClick: photoSize:" + photoSize);
                                if (photoSize > MAX_2_MB) {
                                    int scaleDivider = 4;
                                    try {
                                        // 1. Convert uri to bitmap
                                        Bitmap fullBitmap = MediaStore.Images.Media.getBitmap(SignupActivity.this.getContentResolver(), profilePicUri);

                                        // 2. Get the downsized image content as a byte[]
                                        int scaleWidth = fullBitmap.getWidth() / scaleDivider;
                                        int scaleHeight = fullBitmap.getHeight() / scaleDivider;
                                        byte[] downsizedImageBytes =
                                                getDownsizedImageBytes(fullBitmap, scaleWidth, scaleHeight);

                                        if (downsizedImageBytes != null) {
                                            upLoadProfilePicWithBytes(userMain, downsizedImageBytes);
                                        } else {
                                            TravelGuideToast.showErrorToast(SignupActivity.this, "Failed to reduce photo size, try again.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                                        }
                                    } catch (IOException ioEx) {
                                        ioEx.printStackTrace();
                                    }
                                } else {
                                    upLoadProfilePic(userMain, profilePicUri);
                                }
                            } else {
                                TravelGuideToast.showErrorToast(SignupActivity.this, "Mobile number already exists", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                            }
                        }
                    } else {
                        TravelGuideToast.showErrorToast(SignupActivity.this, getString(R.string.no_internet), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                    }
                }

            });

            userProfilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent galleryIntent = new Intent();
                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, 2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upLoadProfilePic(UserMain userMain, Uri profilePicUri) {
        try {
            showProgressDialog("Processing..");

            StorageReference fileRef = storageReference.child(userMain.getMobileNumber() + "." + getFileExtension(profilePicUri));
            fileRef.putFile(profilePicUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            userMain.setProfilePicUrl(uri.toString());
                            signUpNewUser(userMain);
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    hideProgressDialog();
                    TravelGuideToast.showErrorToast(SignupActivity.this, "Failed to upload pic", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void upLoadProfilePicWithBytes(UserMain userMain, byte[] bytePic) {
        try {
            showProgressDialog("Processing..");

            StorageReference fileRef = storageReference.child(userMain.getMobileNumber() + "." + getFileExtension(profilePicUri));
            fileRef.putBytes(bytePic).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            userMain.setProfilePicUrl(uri.toString());
                            signUpNewUser(userMain);
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    hideProgressDialog();
                    TravelGuideToast.showErrorToast(SignupActivity.this, "Failed to upload pic", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(Uri profilePicUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(profilePicUri));
    }

    public void navigateToLogin() {
        try {
            Intent intent = new Intent();
            this.setResult(RESULT_OK, intent);
            this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        try {
            if (UserUtils.isEmptyField(editMobileNumber.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "Please enter mobile number.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (UserUtils.isEmptyField(mPin.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "Please enter your mPin.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (mPin.getText().toString().trim().length() > 4 || mPin.getText().toString().trim().length() < 4) {
                TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "mPin must be four digits", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (UserUtils.isEmptyField(editUserName.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "Please enter full name.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (UserUtils.isEmptyField(textGender.getText().toString().trim())) {
                TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "Please enter gender.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            } else if (profilePicUri == null) {
                TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "Please choose profile picture.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void signUpNewUser(UserMain userMain) {
        try {
            showProgressDialog("Processing please wait.");
            mUserReference.child(userMain.getMobileNumber()).setValue(userMain)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            hideProgressDialog();
                            TravelGuideToast.showSuccessToastWithBottom(SignupActivity.this, "User created successfully.", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_LONG);
                            navigateToLogin();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideProgressDialog();
                            TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, "Failed to register", TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
                        }
                    });
        } catch (Exception e) {
            hideProgressDialog();
            TravelGuideToast.showErrorToastWithBottom(SignupActivity.this, e.getMessage(), TravelGuideToast.TRAVEL_GUIDE_TOAST_LENGTH_SHORT);
            e.printStackTrace();
        }
    }

    public boolean verifyUserRegistration(UserMain userMain) {
        final boolean[] returnValue = {true};
        try {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(FireBaseDatabaseConstants.USERS_TABLE);

            databaseReference.child(userMain.getMobileNumber()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String mobileNumber = snapshot.child(FireBaseDatabaseConstants.MOBILE_NUMBER).getValue(String.class);
                        Log.d(TAG, "onDataChange: userMobileNumber: " + userMain.getMobileNumber());
                        Log.d(TAG, "onDataChange: mobileNumber: " + mobileNumber);
                        if (mobileNumber != null) {
                            if (mobileNumber.equals(userMain.getMobileNumber())) {
                                returnValue[0] = false;
                            } else {
                                returnValue[0] = true;
                            }
                        } else {
                            returnValue[0] = true;
                        }
                    } else {
                        returnValue[0] = true;
                        Log.d(TAG, "onDataChange: snapchat not exists");
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    returnValue[0] = true;
                    Log.d(TAG, "onCancelled: error: " + error.getMessage());
                }
            });
            return returnValue[0];
        } catch (Exception e) {
            e.printStackTrace();
            return returnValue[0];
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


    public byte[] getDownsizedImageBytes(Bitmap fullBitmap, int scaleWidth, int scaleHeight) throws IOException {
        try {
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(fullBitmap, scaleWidth, scaleHeight, true);

            // 2. Instantiate the downsized image content as a byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private long getFileSize(Uri profilePicUri) {
        long fileSize = 0;
        ContentResolver contentResolver = SignupActivity.this.getContentResolver();
        AssetFileDescriptor afd = null;
        try {
            afd = contentResolver.openAssetFileDescriptor(profilePicUri, "r");
            fileSize = afd.getLength();
            afd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSize;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            profilePicUri = data.getData();
            userProfilePic.setImageURI(profilePicUri);
        }

    }
}