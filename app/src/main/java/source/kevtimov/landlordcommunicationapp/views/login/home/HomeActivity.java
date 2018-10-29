package source.kevtimov.landlordcommunicationapp.views.login.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.BitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class HomeActivity extends BaseDrawer implements ContractsHome.Navigator{


    private static final int IDENTIFIER = 631;
    private Toolbar mToolbar;
    private User mLogInUser;

    @Inject
    IBitmapAgent mBitmapAgent;

    @Inject
    HomeFragment mHomeFragment;

    @Inject
    ContractsHome.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incomingUser = getIntent();

        mLogInUser = (User) incomingUser.getSerializableExtra("User");
        mLogInUser.setOnline(true);

        mHomeFragment.setNavigator(this);
        mHomeFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_content, mHomeFragment)
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
        return this.mLogInUser.getUsername();
    }

    @Override
    protected String getEmail() {
        return this.mLogInUser.getEmail();
    }

    @Override
    protected Bitmap getProfilePic() {
        return mBitmapAgent.convertStringToBitmap(this.mLogInUser.getPicture());
    }

    @Override
    protected User getUser() {
        return this.mLogInUser;
    }
}
