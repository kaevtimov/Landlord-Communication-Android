package source.kevtimov.landlordcommunicationapp.utils.drawer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;


public abstract class BaseDrawer extends DaggerAppCompatActivity {


    private Drawer mDrawer;

    public void setupDrawer() {
//        PrimaryDrawerItem myPlaces = new PrimaryDrawerItem()
//                .withIdentifier(MyPlacesActivity.IDENTIFIER)
//                .withName("My places")
//                .withTextColor(Color.WHITE)
//                .withSelectedTextColor(Color.BLACK)
//                .withIcon(R.drawable.);
//
//        SecondaryDrawerItem myConnections = new SecondaryDrawerItem()
//                .withIdentifier(MyConnectionsActivity.IDENTIFIER)
//                .withName("My connections")
//                .withTextColor(Color.WHITE)
//                .withSelectedTextColor(Color.BLACK)
//                .withIcon(R.drawable.);
//
//        AccountHeader headerResult = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.header)
//                .build();

//        mDrawer = new DrawerBuilder()
//                .withActivity(this)
//                .withToolbar(getToolbar())
//                .withSliderBackgroundColor(Color.BLACK)
//                .withAccountHeader(headerResult)
//                .addDrawerItems(
//                        myPlaces,
//                        myConnections
//                )
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    long identifier = drawerItem.getIdentifier();
//
//                    if (getIdentifier() == identifier) {
//                        return false;
//                    }
//
//                    Intent intent = getNextIntent(identifier);
//                    if (intent == null) {
//                        return false;
//                    }
//                    startActivity(intent);
//                    return true;
//                })
//                .build();
    }

    private Intent getNextIntent(long identifier) {
//        if (identifier == MyPlacesActivity.IDENTIFIER) {
//            return new Intent(BaseDrawer.this, MyPlacesActivity.class);
//        } else if (identifier == MyConnectionsActivity.IDENTIFIER) {
//            return new Intent(BaseDrawer.this, MyConnectionsActivity.class);
//        }
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    protected abstract long getIdentifier();

    protected abstract Toolbar getToolbar();
}
