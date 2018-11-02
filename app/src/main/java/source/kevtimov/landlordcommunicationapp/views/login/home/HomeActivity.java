package source.kevtimov.landlordcommunicationapp.views.login.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class HomeActivity extends BaseDrawer implements ContractsHome.Navigator{


    private static final int IDENTIFIER = 631;
    private Toolbar mToolbar;
    private User mLogInUser;

    @Inject
    JsonParser<User> mJsonParser;

    @Inject
    HomeFragment mHomeFragment;

    @Inject
    ContractsHome.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mLogInUser = getUserFromSharedPref();
        mLogInUser.setOnline(true);

        mHomeFragment.setNavigator(this);
        mHomeFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_content, mHomeFragment)
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
        return this.mLogInUser.getUsername();
    }

    @Override
    protected String getEmail() {
        return this.mLogInUser.getEmail();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTheme();
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
