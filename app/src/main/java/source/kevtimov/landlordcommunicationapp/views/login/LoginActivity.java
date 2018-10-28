package source.kevtimov.landlordcommunicationapp.views.login;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.ChatActivity;
import source.kevtimov.landlordcommunicationapp.models.User;

public class LoginActivity extends DaggerAppCompatActivity implements ContractsLogin.Navigator{

    @Inject
    ContractsLogin.Presenter mPresenter;

    @Inject
    LoginFragment mLoginFragment;

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
    }

    @Override
    public void navigateWith(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
