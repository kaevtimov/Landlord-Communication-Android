package source.kevtimov.landlordcommunicationapp.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.ChatActivity;
import source.kevtimov.landlordcommunicationapp.chat.fcm.ChatMessagingService;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.sharedpref.PrefUtil;

public class LoginActivity extends DaggerAppCompatActivity implements ContractsLogin.Navigator{

    @Inject
    ContractsLogin.Presenter mPresenter;

    @Inject
    LoginFragment mLoginFragment;
    private PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        prefUtil = new PrefUtil(this);



        mLoginFragment.setPresenter(mPresenter);
        mLoginFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_content, mLoginFragment)
                .commit();
    }

    @Override
    public void navigateWith(User user) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            String token = instanceIdResult.getToken();
            prefUtil.saveFCMToken(token,user.getUsername());
        });
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}
