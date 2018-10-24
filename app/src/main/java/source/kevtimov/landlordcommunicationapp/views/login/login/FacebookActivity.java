package source.kevtimov.landlordcommunicationapp.views.login.login;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;


public class FacebookActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
    }
}
