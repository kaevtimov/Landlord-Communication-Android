package source.kevtimov.landlordcommunicationapp.chat.sessions;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.UsersAdapter;

public class ChatSessionFragment extends Fragment implements ContractsChatSession.View{


    @BindView(R.id.lv_users)
    ListView mListViewChats;

    @BindView(R.id.progress_bar)
    CircleProgressBar mProgressBar;

    private ContractsChatSession.Presenter mPresenter;
    private ContractsChatSession.Navigator mNavigator;
    private ChatUsersAdapter mChatAdapter;
    private List<User> users;


    @Inject
    public ChatSessionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, root);

        users = new ArrayList<>();

        mChatAdapter = new ChatUsersAdapter(getContext());
        mListViewChats.setAdapter(mChatAdapter);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mChatAdapter.clear();
        mPresenter.loadUsers();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsChatSession.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsChatSession.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void addUser(User user) {
        if(!users.contains(user)){
            users.add(user);
            mChatAdapter.add(user);
        }
    }

    @Override
    public void showEmptyList() {
        StyleableToast.makeText(getContext(), Constants.NO_SESSIONS_FOUND,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void navigateToMessageView(ChatSession chat, User other) {
        mNavigator.navigateToMessageView(chat, other);
    }


    @OnItemClick(R.id.lv_users)
    public void onUserClick(AdapterView<?> parent, View view, int position, long id){
        User user = mChatAdapter.getItem(position);

        mPresenter.checkForChatSession(user);
    }
}
