package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.content.Intent;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ChatRoomContracts {
    interface View {
        void setPresenter(Presenter presenter);

        void displayReceivedMessage(String message);

        void displayMyMessage(Message message);

        void setSender(User user);

        void setReceiver(User user);
    }

    interface Presenter {
        void subscribe(View view);

        void sendMessage(Message message, User to) throws IOException;

        void parseReceivedMessage();

        void loadConversation(String senderToken, String receiverToken);
    }
}
