package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.content.Intent;

import source.kevtimov.landlordcommunicationapp.models.User;

public class ChatRoomPresenter implements ChatRoomContracts.Presenter {
    private ChatRoomContracts.View mView;
    @Override
    public void subscribe(ChatRoomContracts.View view) {
        mView = view;
    }

    @Override
    public void sendMessage(String message, User to) {

    }

    @Override
    public void parseReceivedMessage(Intent intent) {
        
    }
}
