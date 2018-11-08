package source.kevtimov.landlordcommunicationapp.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomActivity;
import source.kevtimov.landlordcommunicationapp.chat.fcm.ChatMessagingService;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;
import source.kevtimov.landlordcommunicationapp.utils.sharedpref.PrefUtil;

public class ChatActivity extends BaseDrawer implements ChatContracts.Navigator {
    public static final long IDENTIFIER = 966;

    private Toolbar mToolbar;
    private PrefUtil prefUtil;

    @Inject
    ChatFragment mChatFragment;

    @Inject
    ChatContracts.Presenter chatPresenter;
    private static User mLoggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mChatFragment.setPresenter(chatPresenter);
        mChatFragment.setNavigator(this);
        mChatFragment.setUser(mLoggedInUser);


        //mLoggedInUser = (User) getIntent().getSerializableExtra("user");

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.landlords,mChatFragment)
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
        return null;
    }

    @Override
    protected String getEmail() {
        return null;
    }

    @Override
    public void navigateToChatRoom(User user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("chatMate",user);
        bundle.putSerializable("chatStarter",mLoggedInUser);
        Intent intent = new Intent(this,ChatRoomActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public static void setLoggedInUser(User user) {
        mLoggedInUser = user;
    }
}
