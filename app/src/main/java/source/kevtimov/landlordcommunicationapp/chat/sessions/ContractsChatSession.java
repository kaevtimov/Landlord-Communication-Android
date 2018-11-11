package source.kevtimov.landlordcommunicationapp.chat.sessions;

import java.util.List;

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

        //void showSessions(List<ChatSession> chatSessions);

        void showEmptyList();

        void navigateToMessageView(ChatSession chat);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        //void getAllByLoggedInUserId();

        void setUser(User user);

        void allowNavigationToMessageView(ChatSession chat);

        void loadUsers();

        void checkForChatSession(User otherUser);
    }

    interface Navigator {

        void navigateToMessageView(ChatSession chat);
    }
}
