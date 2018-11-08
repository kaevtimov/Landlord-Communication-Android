package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class ChatRoomActivity extends BaseDrawer {
    private static final long IDENTIFIER = 102;

    @Inject
    ChatRoomFragment mChatRoomFragment;

    @Inject
    ChatRoomContracts.Presenter mPresenter;

    private Toolbar mToolbar;
    private User mLoggedInUser;
    private User mChatMate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mChatRoomFragment.setPresenter(mPresenter);

        mLoggedInUser = (User) getIntent().getSerializableExtra("chatStarter");
        mChatMate = (User) getIntent().getSerializableExtra("chatMate");

        mChatRoomFragment.setReceiver(mChatMate);
        mChatRoomFragment.setSender(mLoggedInUser);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.chat,mChatRoomFragment)
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
}
