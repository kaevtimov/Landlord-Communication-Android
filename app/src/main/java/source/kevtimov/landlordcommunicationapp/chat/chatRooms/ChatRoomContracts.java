package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.content.Intent;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ChatRoomContracts {
    interface View {
        void setPresenter(Presenter presenter);

        void displayReceivedMessage(String message);

        void displayMyMessage(String message);

        void setSendToUser(User user);

        void setReceiverUser(User user);
    }
    interface Presenter {
        void subscribe(View view);

        void sendMessage(String message, User to);

        void parseReceivedMessage(Intent intent);
    }
}
