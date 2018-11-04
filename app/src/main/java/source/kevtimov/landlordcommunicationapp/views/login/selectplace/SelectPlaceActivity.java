package source.kevtimov.landlordcommunicationapp.views.login.selectplace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;

public class SelectPlaceActivity extends DaggerAppCompatActivity implements ContractsSelectPlace.Navigator{

    @Inject
    SelectPlaceFragment mSelectFragment;

    @Inject
    ContractsSelectPlace.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        mSelectFragment.setNavigator(this);
        mSelectFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.select_place_content, mSelectFragment)
                .commit();

    }

    @Override
    public void navigateToPlaceManagementActivity(ArrayList<Place> mArrayPlaces) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("places", mArrayPlaces);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
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
