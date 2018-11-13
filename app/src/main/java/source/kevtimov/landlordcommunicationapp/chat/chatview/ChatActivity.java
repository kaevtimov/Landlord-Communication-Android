package source.kevtimov.landlordcommunicationapp.chat.chatview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.sessions.ChatSessionFragment;
import source.kevtimov.landlordcommunicationapp.chat.sessions.ContractsChatSession;
import source.kevtimov.landlordcommunicationapp.chat.templatemessage.TemplateMessageActivity;
import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class ChatActivity extends BaseDrawer implements ContractsChat.Navigator{

    @Inject
    ChatFragment mChatFragment;

    @Inject
    ContractsChat.Presenter mPresenter;

    @Inject
    JsonParser<User> mJsonParser;

    private Toolbar mToolbar;
    private User mLogInUser;
    public static final int IDENTIFIER = 268;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ChatSession incomingChatSession = (ChatSession) getIntent().getSerializableExtra("ChatSession");
        User otherUser = (User) getIntent().getSerializableExtra("OtherUser");

        mChatFragment.setNavigator(this);
        mLogInUser = getUserFromSharedPref();
        mPresenter.setLoggedInUser(mLogInUser);
        mPresenter.setSession(incomingChatSession.getChatsessionId());
        mPresenter.setOtherUser(otherUser);

        mChatFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.chat_view, mChatFragment)
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
        return mLogInUser.getUsername();
    }

    @Override
    protected String getEmail() {
        return mLogInUser.getEmail();
    }

    @Override
    public void navigateToTemplateMessageChoose() {
        Intent intent = new Intent(this, TemplateMessageActivity.class);
        startActivityForResult(intent, Constants.REQUEST_CODE_SELECT_TEMPLATE_MESSAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_SELECT_TEMPLATE_MESSAGE) {
            if (resultCode == RESULT_OK) {
                String incomingTemplateMessage = Objects.requireNonNull(data).getStringExtra(Constants.TEMPLATE_MESSAGE);

                mChatFragment.sendTemplateMessage(incomingTemplateMessage);
            }
        }
    }

    private User getUserFromSharedPref() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userInfo = sharedPreferences.getString(Constants.SHARED_PREFERENCES_KEY_USER_INFO, "");
        return mJsonParser.fromJson(userInfo);
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
