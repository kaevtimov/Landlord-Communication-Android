package source.kevtimov.landlordcommunicationapp.views.login.addcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class AddCardActivity extends DaggerAppCompatActivity implements ContractsAddCard.Navigator{

    @Inject
    JsonParser<User> mJsonParser;

    @Inject
    AddCardFragment mAddCardFragment;

    @Inject
    ContractsAddCard.Presenter mPresenter;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        Intent incoming = getIntent();


        mUser = getUser();
        mAddCardFragment.setNavigator(this);
        mAddCardFragment.setPresenter(mPresenter);
        mPresenter.setUser(mUser);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_card_content, mAddCardFragment)
                .commit();
    }

    @Override
    public void navigateBack() {
        finish();
    }

    private User getUser(){
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
