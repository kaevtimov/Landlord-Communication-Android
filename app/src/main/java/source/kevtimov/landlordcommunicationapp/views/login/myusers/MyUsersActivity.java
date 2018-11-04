package source.kevtimov.landlordcommunicationapp.views.login.myusers;

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
import source.kevtimov.landlordcommunicationapp.views.login.userdetails.UserDetailsActivity;

public class MyUsersActivity extends BaseDrawer implements ContractsMyUsers.Navigator{

    @Inject
    MyUsersFragment mUsersFragment;

    @Inject
    ContractsMyUsers.Presenter mPresenter;

    @Inject
    JsonParser<User> mJsonParser;

    public static final int IDENTIFIER = 2354;
    private Toolbar mToolbar;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_users);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mUser = getUserFromSharedPref();
        mPresenter.setUser(mUser);

        mUsersFragment.setPresenter(mPresenter);
        mUsersFragment.setNavigator(this);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.users_content, mUsersFragment)
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
    public void navigateToDetails(User user) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("User", user);
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
