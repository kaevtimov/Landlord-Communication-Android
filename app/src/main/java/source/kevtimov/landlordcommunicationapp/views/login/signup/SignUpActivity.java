package source.kevtimov.landlordcommunicationapp.views.login.signup;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementActivity;

public class SignUpActivity extends DaggerAppCompatActivity implements ContractsSignUp.Navigator {

    @Inject
    SignUpFragment mSignUpFragment;

    @Inject
    ContractsSignUp.Presenter mPresenter;

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
        Intent intent = new Intent(this, PlaceManagementActivity.class);

        intent.putExtra("User", user);

        startActivity(intent);
    }
}
