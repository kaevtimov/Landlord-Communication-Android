package source.kevtimov.landlordcommunicationapp.views.login.signup;

import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.views.login.finishsignup.FinishSignUpActivity;

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
    public void navigateToFinishSignUp(Bundle userInformation) {
        Intent intent = new Intent(this, FinishSignUpActivity.class);

        intent.putExtra("UserData", userInformation);

        startActivity(intent);
    }
}
