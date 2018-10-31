package source.kevtimov.landlordcommunicationapp.chat.chatRooms;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRoomFragment extends Fragment implements ChatRoomContracts.View {
    @BindView(R.id.lv_messages)
    ListView mMessagesListView;

    @BindView(R.id.et_message_text)
    EditText mMessageEditText;

    @BindView(R.id.btn_send)
    ImageButton mSendButton;

    @Inject
    ChatRoomMessageAdapter mMessageAdapter;

    private ChatRoomContracts.Presenter mPresenter;
    private User mSendToUser;
    private User mReceiverUser;

    @Inject
    public ChatRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_room, container, false);
        ButterKnife.bind(this,view);
        mMessagesListView.setAdapter(mMessageAdapter);
        return view;
    }

    @Override
    public void setPresenter(ChatRoomContracts.Presenter presenter) {
        mPresenter = presenter;
    }
    //TODO:Add name to signature
    @Override
    public void displayReceivedMessage(String message) {
        Message chatMateMessage = new Message();
        chatMateMessage.setMessageBody(message);
        chatMateMessage.setSendByMe(false);
        mMessageAdapter.add(chatMateMessage);
    }

    @Override
    public void displayMyMessage(String message) {
        Message myMessage = new Message();
        myMessage.setSendByMe(true);
        myMessage.setMessageBody(message);
        mMessageAdapter.add(myMessage);
    }

    @Override
    public void setSendToUser(User user) {
        mSendToUser = user;
    }

    @Override
    public void setReceiverUser(User user) {
        mReceiverUser = user;
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
        String message = String.valueOf(mMessageEditText.getText());
        mPresenter.sendMessage(message,mSendToUser);
    }
}
