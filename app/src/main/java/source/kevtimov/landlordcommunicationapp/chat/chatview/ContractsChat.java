package source.kevtimov.landlordcommunicationapp.chat.chatview;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsChat {

    interface View {

        void setPresenter(ContractsChat.Presenter presenter);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void showMessages(List<Message> messages);

    }

    interface Presenter {

        void subscribe(ContractsChat.View view);

        void unsubscribe();

        void getAllMessagesByChatId();

        void setLoggedInUser(int userId);

        void setOtherUser(int userId);

        void setSession(int chatSessionId);

        void createMessage(String msgContent);
    }
}
