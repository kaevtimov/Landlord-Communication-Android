package source.kevtimov.landlordcommunicationapp.chat.templatemessage;


import java.util.List;

public interface ContractsTemplateMessage {

    interface View {

        void setPresenter(ContractsTemplateMessage.Presenter presenter);

        void showMessages(List<String> messages);

        void navigateToChat(String message);

        void setNavigator(ContractsTemplateMessage.Navigator navigator);

    }

    interface Presenter {

        void subscribe(ContractsTemplateMessage.View view);

        void unsubscribe();

        void allowNavigationToChat(String message);

        void loadTemplateMessages();

        void addTemplateMessage(String message);
    }

    interface Navigator{
        void navigateBackToChat(String templateMessage);
    }
}
