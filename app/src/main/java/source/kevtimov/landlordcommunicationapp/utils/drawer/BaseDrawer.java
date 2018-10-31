package source.kevtimov.landlordcommunicationapp.utils.drawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;
import source.kevtimov.landlordcommunicationapp.views.login.mypayments.MyPaymentsActivity;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesActivity;


public abstract class BaseDrawer extends DaggerAppCompatActivity {

    @Inject
    IBitmapAgent mBitmapAgent;

    private Drawer mDrawer;

    public void setupDrawer() {
        PrimaryDrawerItem myPlaces = new PrimaryDrawerItem()
                .withIdentifier(MyPlacesActivity.IDENTIFIER)
                .withName("My places")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.BLACK)
                .withIcon(R.drawable.ic_home_black_24dp);

        SecondaryDrawerItem myPayments = new SecondaryDrawerItem()
                .withIdentifier(MyPaymentsActivity.IDENTIFIER)
                .withName("My payments")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.BLACK)
                .withIcon(R.drawable.money);

        IProfile profile = new ProfileDrawerItem ()
                .withName (getUsername())
                .withEmail (getEmail())
                .withIcon (mBitmapAgent.convertStringToBitmap(getProfilePic()));

        AccountHeader accHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorHeaderBack)
                .addProfiles(profile)
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .withSliderBackgroundColor(Color.BLACK)
                .withAccountHeader(accHeader)
                .addDrawerItems(
                        myPlaces,
                        myPayments
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    long identifier = drawerItem.getIdentifier();

                    if (getIdentifier() == identifier) {
                        return false;
                    }
                    Intent intent = getNextIntent(identifier);
                    if (intent == null) {
                        return false;
                    }
                    startActivity(intent);
                    return true;
                })
                .build();
    }

    private Intent getNextIntent(long identifier) {
        if (identifier == MyPlacesActivity.IDENTIFIER) {
            Intent intent = new Intent(BaseDrawer.this, MyPlacesActivity.class);
            return intent;
        } else if (identifier == MyPaymentsActivity.IDENTIFIER) {
            Intent intent = new Intent(BaseDrawer.this, MyPaymentsActivity.class);
            return intent;
        }
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    protected abstract long getIdentifier();

    protected abstract Toolbar getToolbar();

    protected abstract String getUsername();

    protected abstract String getEmail();

    protected abstract String getProfilePic();
}
