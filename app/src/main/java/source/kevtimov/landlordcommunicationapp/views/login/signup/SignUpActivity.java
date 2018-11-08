package source.kevtimov.landlordcommunicationapp.views.login.signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementActivity;

public class SignUpActivity extends DaggerAppCompatActivity implements ContractsSignUp.Navigator {

    @Inject
    SignUpFragment mSignUpFragment;

    @Inject
    ContractsSignUp.Presenter mPresenter;

    @Inject
    JsonParser<User> mJsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent incoming = getIntent();

        Bundle incomingData = incoming.getBundleExtra("register_bundle");

        mSignUpFragment.setBundleWithUserInfo(incomingData);

        mSignUpFragment.setPresenter(mPresenter);
        mSignUpFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signup_content, mSignUpFragment)
                .commit();
    }

    @Override
    public void navigateToPlaceManagement(User user) {
        manageUserInSharedPref(user);
        Intent intent = new Intent(this, PlaceManagementActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Constants.REQUEST_CODE_GALLERY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE_GALLERY && resultCode
                == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                mSignUpFragment.setImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void manageUserInSharedPref(User user) {
        String userInfo = mJsonParser.toJson(user);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.SHARED_PREFERENCES_KEY_USER_INFO, userInfo);
        editor.apply();
    }
}
