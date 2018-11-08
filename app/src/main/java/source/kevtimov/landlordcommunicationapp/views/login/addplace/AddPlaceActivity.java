package source.kevtimov.landlordcommunicationapp.views.login.addplace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantActivity;

public class AddPlaceActivity extends DaggerAppCompatActivity implements ContractsAddPlace.Navigator {

    @Inject
    AddPlaceFragment mAddPlaceFragment;

    @Inject
    ContractsAddPlace.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        mAddPlaceFragment.setPresenter(mPresenter);
        mAddPlaceFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.addplace_content, mAddPlaceFragment)
                .commit();

    }


    @Override
    public void navigateToPlaceManagementActivity(Bundle info) {
        Intent intent = new Intent();

        intent.putExtra("PlaceAndRent", info);

        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public void navigateOnCancel() {
        this.finish();
    }

    @Override
    public void navigateToSelectTenant() {
        Intent intent = new Intent(this, SelectTenantActivity.class);

        startActivityForResult(intent, Constants.SELECT_TENANT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.SELECT_TENANT_REQUEST) {
            if (resultCode == RESULT_OK) {

                User incomingTenant = (User) data.getSerializableExtra("User");

                mAddPlaceFragment.setUserTenant(incomingTenant);
            }
        }
    }

    private void setTheme(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme =  Integer.parseInt(sharedPreferences.getString("theme_list", "1"));

        switch(theme){
            case 1:
                setTheme(R.style.AppThemeCustom);
                break;
            case 2:
                setTheme(R.style.AppThemeCustomDark);
                break;

        }
    }
}
