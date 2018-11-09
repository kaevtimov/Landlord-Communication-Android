package source.kevtimov.landlordcommunicationapp.views.login.selecttenant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class SelectTenantActivity extends DaggerAppCompatActivity implements ContractsSelectTenant.Navigator {

    @Inject
    SelectTenantFragment mSelectTenantFragment;

    @Inject
    ContractsSelectTenant.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tenant);

        mSelectTenantFragment.setNavigator(this);
        mSelectTenantFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.select_tenant_content, mSelectTenantFragment)
                .commit();
    }

    @Override
    public void navigateToAddPlaceOnCancel() {
        this.finish();
    }

    @Override
    public void navigateToAddPlaceOnDone(User tenant) {
        Intent intent = new Intent();
        intent.putExtra("User", tenant);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setTheme(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int theme =  Integer.parseInt(sharedPreferences.getString(Constants.THEME_LIST, "1"));

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
