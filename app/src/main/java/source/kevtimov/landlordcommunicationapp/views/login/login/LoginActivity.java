package source.kevtimov.landlordcommunicationapp.views.login.login;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeActivity;
import source.kevtimov.landlordcommunicationapp.views.login.signup.SignUpActivity;

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
    public void navigateToSignUp(Bundle registerBundle) {

        Intent intent = new Intent(this, SignUpActivity.class);

        intent.putExtra("register_bundle", registerBundle);

        startActivity(intent);

        finish();
    }

    @Override
    public void navigateToHome(User user) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
        finish();
    }
}
