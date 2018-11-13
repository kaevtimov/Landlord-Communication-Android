package source.kevtimov.landlordcommunicationapp.chat.sessions;

import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsChatSession {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void addUser(User user);

        void showEmptyList();

        void navigateToMessageView(ChatSession chat, User otherUser);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUser(User user);

        void loadUsers();

        void checkForChatSession(User otherUser);
    }

    interface Navigator {

        void navigateToMessageView(ChatSession chat, User otherUser);
    }
}
