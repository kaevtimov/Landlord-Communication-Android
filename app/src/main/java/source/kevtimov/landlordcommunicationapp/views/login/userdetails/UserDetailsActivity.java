package source.kevtimov.landlordcommunicationapp.views.login.userdetails;

import android.content.Intent;
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

public class UserDetailsActivity extends BaseDrawer {

    @Inject
    UserDetailsFragment mFragment;

    @Inject
    ContractsUserDetails.Presenter mPresenter;

    @Inject
    JsonParser<User> mJsonParser;


    private Toolbar mToolbar;
    public static final int IDENTIFIER = 349;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incoming = getIntent();

        User income = (User) incoming.getSerializableExtra("User");
        mUser = getUserFromSharedPref();

        mFragment.setPresenter(mPresenter);
        mPresenter.setLoggedInUser(mUser);
        mPresenter.setOtherUser(income);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.details_content, mFragment)
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

    private User getUserFromSharedPref() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userInfo = sharedPreferences.getString(Constants.SHARED_PREFERENCES_KEY_USER_INFO, "");
        return mJsonParser.fromJson(userInfo);
    }

    private void setTheme(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme =  Integer.parseInt(sharedPreferences.getString(Constants.THEME_LIST, "1"));

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
