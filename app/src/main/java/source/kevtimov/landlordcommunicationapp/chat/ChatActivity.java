package source.kevtimov.landlordcommunicationapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomActivity;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class ChatActivity extends BaseDrawer implements ChatContracts.Navigator {
    public static final long IDENTIFIER = 966;

    private Toolbar mToolbar;

    @Inject
    ChatFragment mChatFragment;

    @Inject
    ChatContracts.Presenter chatPresenter;
    private User mLoggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mChatFragment.setPresenter(chatPresenter);
        mChatFragment.setNavigator(this);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mLoggedInUser = (User) getIntent().getSerializableExtra("user");

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
    public void navigateToChatRoom(User user) {
        Intent intent = new Intent(this,ChatRoomActivity.class);
        intent.putExtra("chatMate",user);
        intent.putExtra("chatStarter",mLoggedInUser);
        startActivity(intent);
    }
}
