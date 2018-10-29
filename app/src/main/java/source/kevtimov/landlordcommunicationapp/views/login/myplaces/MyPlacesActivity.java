package source.kevtimov.landlordcommunicationapp.views.login.myplaces;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementActivity;

public class MyPlacesActivity extends BaseDrawer implements ContractsMyPlaces.Navigator{

    @Inject
    MyPlacesFragment mMyPlacesFragment;

    @Inject
    ContractsMyPlaces.Presenter mPresenter;

    @Inject
    IBitmapAgent mBitmapAgent;

    private Toolbar mToolbar;
    public static final int IDENTIFIER = 923;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incomingUser = getIntent();
        mUser = (User) incomingUser.getSerializableExtra("User");

        mMyPlacesFragment.setNavigator(this);
        mMyPlacesFragment.setPresenter(mPresenter);
        mPresenter.setUser(mUser);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.my_places_content, mMyPlacesFragment)
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
    protected Bitmap getProfilePic() {
        return mBitmapAgent.convertStringToBitmap(this.mUser.getPicture());
    }

    @Override
    protected User getUser() {
        return this.mUser;
    }

    @Override
    public void navigateToPlaceDetails(Place place) {

    }

    @Override
    public void navigateToManagePlace() {
        Intent intent = new Intent(this, PlaceManagementActivity.class);

        intent.putExtra("User", mUser);

        startActivity(intent);
    }
}
