package source.kevtimov.landlordcommunicationapp.chat.chatview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.chat.sessions.ContractsChatSession;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class ChatFragment extends Fragment implements ContractsChat.View{


    @BindView(R.id.iv_other_user)
    RoundedImageView mImageViewOtherUser;

    @BindView(R.id.chat_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.chat_input_msg)
    EditText mEditTextSendMsg;

    @BindView(R.id.progress_bar)
    CircleProgressBar mProgressBar;


    private ContractsChat.Presenter mPresenter;
    private ContractsChat.Navigator mNavigator;
    private ChatMessageAdapter mChatAdapter;
    private ArrayList<Message> mMessageList;
    private LinearLayoutManager mLinearLayoutManager;

    @Inject
    public ChatFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_chat_view, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, root);

        initViews();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.getAllMessagesByChatId();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater
                .inflate(R.menu.chat_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId){
            case R.id.menu_message_template:
                mPresenter.allowNavigationToTemplateMessages();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void setPresenter(ContractsChat.Presenter presenter) {
        this.mPresenter = presenter;
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
    public void setNavigator(ContractsChat.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void navigateToMessageTemplates() {
        mNavigator.navigateToTemplateMessageChoose();
    }

    @Override
    public void showMessages(List<Message> messages) {
        mMessageList.clear();
        mMessageList.addAll(messages);
        mChatAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mChatAdapter.getItemCount()-1);
    }

    @Override
    public void sendTemplateMessage(String incomingTemplateMessage) {
        mEditTextSendMsg.setText(incomingTemplateMessage);
    }

    @OnClick(R.id.chat_send_msg)
    public void onSendClick(View v){
        String msgContent = mEditTextSendMsg.getText().toString();
        if(!TextUtils.isEmpty(msgContent)) {

            mPresenter.createMessage(msgContent);

            mEditTextSendMsg.setText("");
        }
    }

    private void initViews() {
        mImageViewOtherUser.setImageBitmap(mPresenter.setOtherUserPicture());
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageList = new ArrayList<>();

        mChatAdapter = new ChatMessageAdapter(mMessageList, getContext());
        mRecyclerView.setAdapter(mChatAdapter);
    }
}
