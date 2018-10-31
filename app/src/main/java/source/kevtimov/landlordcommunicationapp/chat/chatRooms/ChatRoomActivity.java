package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
    private User mLoggerInUser;
    private User mChatMate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Intent incoming = getIntent();
        mLoggerInUser = (User) incoming.getSerializableExtra("chatStarter");
        mChatMate = (User) incoming.getSerializableExtra("chatMate");
        mChatRoomFragment.setPresenter(mPresenter);
        mChatRoomFragment.setReceiverUser(mLoggerInUser);
        mChatRoomFragment.setSendToUser(mChatMate);
    }

    @Override
    protected long getIdentifier() {
        return 0;
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }
}
