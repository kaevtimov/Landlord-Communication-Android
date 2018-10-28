package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.AddPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.SelectPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantActivity;

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

        User user = (User) incoming.getSerializableExtra("User");

        mPlaceManagementFragment.setUser(user);

        mPlaceManagementFragment.setPresenter(mPresenter);
        mPlaceManagementFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signup_finish_content, mPlaceManagementFragment)
                .commit();
    }

    @Override
    public void navigateToHomeActivity(User userInfo) {
        Intent intent = new Intent(this, HomeActivity.class);

        intent.putExtra("User", userInfo);

        startActivity(intent);
    }

    @Override
    public void navigateToAddPlaceActivity() {
        Intent intent = new Intent(this, AddPlaceActivity.class);

        startActivityForResult(intent, Constants.ADD_PLACE_REQUEST);
    }

    @Override
    public void navigateToSelectPlaceActivity() {
        Intent intent = new Intent(this, SelectPlaceActivity.class);

        startActivityForResult(intent, Constants.SELECT_PLACE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.subscribe(mPlaceManagementFragment);
        if (requestCode == Constants.ADD_PLACE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle incomingInfo = data.getBundleExtra("PlaceAndRent");
                mPlaceManagementFragment.updateAddPlaces(incomingInfo);
            }
        } else if (requestCode == Constants.SELECT_PLACE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                ArrayList<Place> placesList = (ArrayList<Place>) bundle.getSerializable("places");
                mPlaceManagementFragment.updatePlacesInDatabase(placesList);
                mPlaceManagementFragment.updateSelectPlaces(placesList);
            }
        }
    }
}
