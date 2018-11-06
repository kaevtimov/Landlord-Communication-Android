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

        void setSendToUser(User user);

        void setReceiverUser(User user);
    }

    interface Presenter {
        void subscribe(View view);

        void sendMessage(String message, User to) throws IOException;

        void parseReceivedMessage(Intent intent);

        void loadConversation(String senderToken, String receiverToken);
    }
}
