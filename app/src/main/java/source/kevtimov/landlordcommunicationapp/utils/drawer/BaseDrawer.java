package source.kevtimov.landlordcommunicationapp.utils.drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcache.BitmapCache;
import source.kevtimov.landlordcommunicationapp.views.login.mypayments.MyPaymentsActivity;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesActivity;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.MyUsersActivity;
import source.kevtimov.landlordcommunicationapp.views.login.preferences.SettingsActivity;


public abstract class BaseDrawer extends DaggerAppCompatActivity {


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
                .withIcon(R.drawable.ic_monetization_on_black_24dp);

        SecondaryDrawerItem settings = new SecondaryDrawerItem()
                .withIdentifier(SettingsActivity.IDENTIFIER)
                .withName("Settings")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.BLACK)
                .withIcon(R.drawable.ic_settings_black_24dp);

        SecondaryDrawerItem myUsers = new SecondaryDrawerItem()
                .withIdentifier(MyUsersActivity.IDENTIFIER)
                .withName("My users")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.BLACK)
                .withIcon(R.drawable.ic_supervisor_account_24dp);

        IProfile profile = new ProfileDrawerItem ()
                .withName (getUsername())
                .withEmail (getEmail())
                .withIcon (getProfilePicture());

        AccountHeader accHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(getColorHeader())
                .addProfiles(profile)
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .withSliderBackgroundColor(Color.BLACK)
                .withAccountHeader(accHeader)
                .addDrawerItems(
                        myPlaces,
                        myPayments,
                        myUsers,
                        settings
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
                    mDrawer.closeDrawer();
                    return true;
                })
                .withCloseOnClick(true)
                .build();
    }

    private Intent getNextIntent(long identifier) {
        if (identifier == MyPlacesActivity.IDENTIFIER) {
            Intent intent = new Intent(BaseDrawer.this, MyPlacesActivity.class);
            return intent;
        } else if (identifier == MyPaymentsActivity.IDENTIFIER) {
            Intent intent = new Intent(BaseDrawer.this, MyPaymentsActivity.class);
            return intent;
        } else if (identifier == SettingsActivity.IDENTIFIER) {
            Intent intent = new Intent(BaseDrawer.this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            return intent;
        } else if (identifier == MyUsersActivity.IDENTIFIER) {
            Intent intent = new Intent(BaseDrawer.this, MyUsersActivity.class);
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

    private int getColorHeader(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme =  Integer.parseInt(sharedPreferences.getString("theme_list", "1"));

        switch(theme){
            case 1:
                return R.color.colorHeaderBack;
            case 2:
                return R.color.md_green_600;

        }
        return R.color.colorHeaderBack;
    }

    private Bitmap getProfilePicture(){
        Bitmap profPicture = (Bitmap) BitmapCache.getInstance().getLruCache().get("logged_in_user_profile_image");

        return profPicture;
    }
}
