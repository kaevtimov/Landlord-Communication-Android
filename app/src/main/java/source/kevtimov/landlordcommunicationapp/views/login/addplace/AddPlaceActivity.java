package source.kevtimov.landlordcommunicationapp.views.login.addplace;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class AddPlaceActivity extends DaggerAppCompatActivity implements ContractsAddPlace.Navigator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
    }

    @Override
    public void navigateToHome(User user) {

    }
}
