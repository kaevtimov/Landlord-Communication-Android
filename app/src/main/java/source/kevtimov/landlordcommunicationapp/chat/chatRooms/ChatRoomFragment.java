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

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatRoomFragment extends Fragment implements ChatRoomContracts.View, View.OnClickListener {
    @BindView(R.id.lv_messages)
    ListView mMessagesListView;

    @BindView(R.id.et_message_text)
    EditText mMessageEditText;

    @BindView(R.id.btn_send)
    ImageButton mSendButton;

    private ChatRoomMessageAdapter mMessageAdapter;

    private ChatRoomContracts.Presenter mPresenter;
    private User mSender;
    private User mReceiver;

    @Inject
    public ChatRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_room, container, false);
        ButterKnife.bind(this,view);
        mMessageAdapter = new ChatRoomMessageAdapter(getContext());
        mMessagesListView.setAdapter(mMessageAdapter);
        mSendButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        //mPresenter.loadConversation(mSender.getToken(),mReceiver.getToken());
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
        mMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayMyMessage(Message message) {
        Message myMessage = new Message();
        myMessage.setSendByMe(true);
        myMessage.setMessageBody(message.getMessageBody());
        mMessageAdapter.add(myMessage);
        mMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSendToUser(User user) {
        mSender = user;
    }

    @Override
    public void setReceiverUser(User user) {
        mReceiver = user;
    }

    @Override
    public void onClick(View view) {
        String message = String.valueOf(mMessageEditText.getText());
        Long tsLong = System.currentTimeMillis()/1000;
        String ts  = tsLong.toString();
        try {
            mPresenter.sendMessage(message, mSender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
