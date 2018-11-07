package source.kevtimov.landlordcommunicationapp.views.login.login;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeActivity;
import source.kevtimov.landlordcommunicationapp.views.login.signup.SignUpActivity;

public class LoginActivity extends DaggerAppCompatActivity implements ContractsLogin.Navigator {

    @Inject
    ContractsLogin.Presenter mPresenter;

    @Inject
    LoginFragment mLoginFragment;

    @Inject
    JsonParser<User> mJsonParser;

    public static final int IDENTIFIER = 238;
    private NotificationChannel mNotificationRentChannel;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);
        mLoginFragment.setPresenter(mPresenter);
        mLoginFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_content, mLoginFragment)
                .commit();

        manageNotifications();
    }


    @Override
    public void navigateToSignUp(Bundle registerBundle) {

        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra("register_bundle", registerBundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToHome(User user) {
        manageUserInSharedPref(user);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void manageNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Rent channel notification";
            String description = "This channel is for sending notifications to users when there are " +
                    "five days before meeting the rent due date.";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mNotificationRentChannel = new NotificationChannel(Constants.CHANNEL_ID, name, importance);
            mNotificationRentChannel.setDescription(description);
            mNotificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(mNotificationManager).createNotificationChannel(mNotificationRentChannel);
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
