package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class PlaceManagementActivity extends DaggerAppCompatActivity implements ContractsPlaceManagement.Navigator {

    @Inject
    PlaceManagementFragment mPlaceManagementFragment;

    @Inject
    ContractsPlaceManagement.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_management);

        Intent incoming = getIntent();

        User user = (User)incoming.getSerializableExtra("User");

        mPlaceManagementFragment.setUser(user);

        mPlaceManagementFragment.setPresenter(mPresenter);
        mPlaceManagementFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signup_finish_content, mPlaceManagementFragment)
                .commit();
    }

    @Override
    public void navigateToHomeActivity(Bundle userInformation) {

    }
}
