package source.kevtimov.landlordcommunicationapp.chat.chatview;

import android.graphics.Bitmap;

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

        void setNavigator(ContractsChat.Navigator navigator);

        void navigateToMessageTemplates();

        void showMessages(List<Message> messages);

        void sendTemplateMessage(String incomingTemplateMessage);
    }

    interface Presenter {

        void subscribe(ContractsChat.View view);

        void unsubscribe();

        void getAllMessagesByChatId();

        void setLoggedInUser(User user);

        void setOtherUser(User user);

        void setSession(int chatSessionId);

        void createMessage(String msgContent);

        void allowNavigationToTemplateMessages();

        Bitmap setOtherUserPicture();

    }

    interface Navigator{
        void navigateToTemplateMessageChoose();
    }
}
