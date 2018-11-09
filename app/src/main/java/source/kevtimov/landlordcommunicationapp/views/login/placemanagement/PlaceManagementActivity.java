package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.AddPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.SelectPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantActivity;

public class PlaceManagementActivity extends BaseDrawer implements ContractsPlaceManagement.Navigator {

    @Inject
    PlaceManagementFragment mPlaceManagementFragment;

    @Inject
    ContractsPlaceManagement.Presenter mPresenter;

    @Inject
    JsonParser<User> mJsonParser;

    public static final int IDENTIFIER = 608;
    private User mUser;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_management);
        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incoming = getIntent();

        mUser = getUserFromSharedPref();

        mPlaceManagementFragment.setUser(mUser);
        mPlaceManagementFragment.setPresenter(mPresenter);
        mPlaceManagementFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signup_finish_content, mPlaceManagementFragment)
                .commit();
    }

    @Override
    public void navigateToHomeActivity(User user) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToAddPlaceActivity() {
        Intent intent = new Intent(this, AddPlaceActivity.class);
        startActivityForResult(intent, Constants.ADD_PLACE_REQUEST);
    }

    @Override
    public void navigateToSelectPlaceActivity() {
        Intent intent = new Intent(this, SelectPlaceActivity.class);
        startActivityForResult(intent, Constants.SELECT_PLACE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.subscribe(mPlaceManagementFragment);
        if (requestCode == Constants.ADD_PLACE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle incomingInfo = data.getBundleExtra("PlaceAndRent");
                mPlaceManagementFragment.updateAddPlaces(incomingInfo);
            }
        } else if (requestCode == Constants.SELECT_PLACE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                ArrayList<Place> placesList = (ArrayList<Place>) bundle.getSerializable("places");
                mPlaceManagementFragment.updateSelectPlaces(placesList);
            }
        }
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected String getUsername() {
        return mUser.getUsername();
    }

    @Override
    protected String getEmail() {
        return mUser.getEmail();
    }

    private User getUserFromSharedPref() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userInfo = sharedPreferences.getString(Constants.SHARED_PREFERENCES_KEY_USER_INFO, "");
        return mJsonParser.fromJson(userInfo);
    }

    private void setTheme(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme =  Integer.parseInt(sharedPreferences.getString("theme_list", "1"));

        switch(theme){
            case 1:
                setTheme(R.style.AppThemeCustom);
                break;
            case 2:
                setTheme(R.style.AppThemeCustomDark);
                break;

        }
    }
}
