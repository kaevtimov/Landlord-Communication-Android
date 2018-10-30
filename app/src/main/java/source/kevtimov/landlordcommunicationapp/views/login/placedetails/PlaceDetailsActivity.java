package source.kevtimov.landlordcommunicationapp.views.login.placedetails;

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
import source.kevtimov.landlordcommunicationapp.views.login.payment.PaymentActivity;

public class PlaceDetailsActivity extends BaseDrawer implements ContractsPlaceDetails.Navigator{

    public static final int IDENTIFIER = 591;
    private User mUser;
    private Place mPlace;
    private Toolbar mToolbar;

    @Inject
    PlaceDetailsFragment mPlaceDetailsFragment;

    @Inject
    ContractsPlaceDetails.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incoming = getIntent();

        mUser = (User) incoming.getSerializableExtra("User");

        mPlace = (Place)incoming.getSerializableExtra("Place");

        mPresenter.setUser(mUser);
        mPresenter.setPlace(mPlace);

        mPlaceDetailsFragment.setNavigator(this);
        mPlaceDetailsFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.details_content, mPlaceDetailsFragment)
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
    protected String getProfilePic() {
        return this.mUser.getPicture();
    }

    @Override
    protected User getUser() {
        return this.mUser;
    }

    @Override
    public void navigateToPayRent(Place place) {
        Intent intent = new Intent(this, PaymentActivity.class);

        intent.putExtra("Place", place);
        intent.putExtra("User", mUser);

        startActivity(intent);
        finish();
    }
}
