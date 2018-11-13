package source.kevtimov.landlordcommunicationapp.chat.templatemessage;


public interface ContractsTemplateMessage {

    interface View {

        void setPresenter(ContractsTemplateMessage.Presenter presenter);

        void showMessages(String message);

        void navigateToChat(String message);

        void setNavigator(ContractsTemplateMessage.Navigator navigator);

    }

    interface Presenter {

        void subscribe(ContractsTemplateMessage.View view);

        void unsubscribe();

        void allowNavigationToChat(String message);

        void loadTemplateMessages();
    }

    interface Navigator{
        void navigateBackToChat(String templateMessage);
    }
}
