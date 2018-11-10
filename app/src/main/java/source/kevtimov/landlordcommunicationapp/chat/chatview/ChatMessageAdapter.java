package source.kevtimov.landlordcommunicationapp.chat.chatview;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ChatViewHolder> {


    private JsonParser<User> mJsonParser;
    private List<Message> messageList;
    private User mUser;
    private Context mContext;

    public ChatMessageAdapter(List<Message> msgDtoList, Context context) {
        this.mJsonParser = new GsonJsonParser<>(User.class, User[].class);
        this.messageList = msgDtoList;
        this.mContext = context;
        this.mUser = getUserFromSharedPref();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.chat_item_view, viewGroup, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (message.getSenderID() == mUser.getUserId()) {
            holder.mRightMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.mRightMsgTextView.setText(message.getText());
            holder.mLeftMsgLayout.setVisibility(LinearLayout.GONE);
        }
        else if (message.getReceiverID() == mUser.getUserId()){
            holder.mLeftMsgLayout.setVisibility(LinearLayout.VISIBLE);
            holder.mLeftMsgTextView.setText(message.getText());
            holder.mRightMsgLayout.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        return messageList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLeftMsgLayout;
        LinearLayout mRightMsgLayout;
        TextView mLeftMsgTextView;
        TextView mRightMsgTextView;

        ChatViewHolder(View itemView) {
            super(itemView);

            if (itemView != null) {
                mLeftMsgLayout = itemView.findViewById(R.id.chat_left_msg_layout);
                mRightMsgLayout = itemView.findViewById(R.id.chat_right_msg_layout);
                mLeftMsgTextView = itemView.findViewById(R.id.chat_left_msg_text_view);
                mRightMsgTextView = itemView.findViewById(R.id.chat_right_msg_text_view);
            }
        }
    }

    private User getUserFromSharedPref() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String userInfo = sharedPreferences.getString(Constants.SHARED_PREFERENCES_KEY_USER_INFO, "");
        return mJsonParser.fromJson(userInfo);
    }
}
