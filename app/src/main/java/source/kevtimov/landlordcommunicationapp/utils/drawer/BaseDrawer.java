package source.kevtimov.landlordcommunicationapp.utils.drawer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.chat.ChatActivity;

public abstract class BaseDrawer extends DaggerAppCompatActivity {
    private Drawer mDrawer;

    public void setupDrawer() {
        PrimaryDrawerItem contacts = new PrimaryDrawerItem()
                .withIdentifier(ChatActivity.IDENTIFIER)
                .withName("Contacts")
                .withTextColor(Color.WHITE)
                .withSelectedTextColor(Color.BLACK);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .addDrawerItems(
                        contacts
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
        if (identifier == ChatActivity.IDENTIFIER) {
            return new Intent(BaseDrawer.this, ChatActivity.class);
        }
        return null;
    }
    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    protected abstract long getIdentifier ();

    protected abstract Toolbar getToolbar ();
}

