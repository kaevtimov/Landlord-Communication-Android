package source.kevtimov.landlordcommunicationapp.views.login.myplaces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;
import source.kevtimov.landlordcommunicationapp.views.login.placedetails.PlaceDetailsActivity;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementActivity;

public class MyPlacesActivity extends BaseDrawer implements ContractsMyPlaces.Navigator{

    @Inject
    MyPlacesFragment mMyPlacesFragment;

    @Inject
    JsonParser<User> mJsonParser;

    @Inject
    ContractsMyPlaces.Presenter mPresenter;

    private Toolbar mToolbar;
    public static final int IDENTIFIER = 923;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incomingUser = getIntent();
        mUser = getUserFromSharedPref();

        mMyPlacesFragment.setNavigator(this);
        mMyPlacesFragment.setPresenter(mPresenter);
        mPresenter.setUser(mUser);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.my_places_content, mMyPlacesFragment)
                .commit();
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

    @Override
    public void navigateToPlaceDetails(Place place) {
        Intent intent = new Intent(this, PlaceDetailsActivity.class);

        intent.putExtra("Place", place);

        startActivity(intent);
    }

    @Override
    public void navigateToManagePlace() {
        Intent intent = new Intent(this, PlaceManagementActivity.class);

        startActivity(intent);
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
