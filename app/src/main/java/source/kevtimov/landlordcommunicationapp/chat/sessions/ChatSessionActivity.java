package source.kevtimov.landlordcommunicationapp.chat.sessions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.chatview.ChatActivity;
import source.kevtimov.landlordcommunicationapp.chat.chatview.ContractsChat;
import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class ChatSessionActivity extends BaseDrawer implements ContractsChatSession.Navigator {

    @Inject
    ChatSessionFragment chatSessionFragment;

    @Inject
    ContractsChatSession.Presenter mPresenter;

    @Inject
    JsonParser<User> mJsonParser;

    private Toolbar mToolbar;
    private User mLogInUser;
    public static final int IDENTIFIER = 485;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mToolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mLogInUser = getUserFromSharedPref();
        mPresenter.setUser(mLogInUser);

        chatSessionFragment.setPresenter(mPresenter);
        chatSessionFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.chat_content, chatSessionFragment)
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
    public void navigateToMessageView(ChatSession chat) {
        Intent intent = new Intent(this, ChatActivity.class);

        intent.putExtra("ChatSession", chat);

        startActivity(intent);
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
