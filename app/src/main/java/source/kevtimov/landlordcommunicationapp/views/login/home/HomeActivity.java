package source.kevtimov.landlordcommunicationapp.views.login.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class HomeActivity extends BaseDrawer implements ContractsHome.Navigator{


    private static final int IDENTIFIER = 631;
    private Toolbar mToolbar;

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
}
