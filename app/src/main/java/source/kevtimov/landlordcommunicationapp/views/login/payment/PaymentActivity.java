package source.kevtimov.landlordcommunicationapp.views.login.payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;
import source.kevtimov.landlordcommunicationapp.views.login.addcard.AddCardActivity;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesActivity;
import source.kevtimov.landlordcommunicationapp.views.login.placedetails.PlaceDetailsActivity;

public class PaymentActivity extends BaseDrawer implements ContractsPayments.Navigator{

    @Inject
    PaymentFragment mPaymentFragment;

    @Inject
    JsonParser<User> mJsonParser;

    @Inject
    ContractsPayments.Presenter mPresenter;

    public static final int IDENTIFIER = 916;
    private Toolbar mToolbar;
    private User mUser;
    private Place mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incoming = getIntent();
        mUser = getUserFromSharedPref();
        mPlace = (Place) incoming.getSerializableExtra("Place");

        mPresenter.setPlace(mPlace);
        mPresenter.setUser(mUser);

        mPaymentFragment.setNavigator(this);
        mPaymentFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.payment_content, mPaymentFragment)
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
        return mUser.getPicture();
    }

    @Override
    protected User getUser() {
        return mUser;
    }

    @Override
    public void navigateToMyPlacesActivity() {
        finish();
    }

    @Override
    public void navigateToAddCard() {
        Intent intent = new Intent(this, AddCardActivity.class);
        startActivity(intent);
    }

    private User getUserFromSharedPref() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userInfo = sharedPreferences.getString(Constants.SHARED_PREFERENCES_KEY_USER_INFO, "");
        return mJsonParser.fromJson(userInfo);
    }
}
