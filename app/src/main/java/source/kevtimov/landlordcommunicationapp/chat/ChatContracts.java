package source.kevtimov.landlordcommunicationapp.chat;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ChatContracts {
    interface Presenter {

        void selectLandlord(User landlord);

        void subscribe(View view);

        void loadLandlords(boolean isLandlord);

    }

    interface View {
        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLandlords(List<User> landlordsList);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

        void showEmptyLandlordList();

        void openChatRoom(User user);

        void setUser(User user);
    }

    interface Navigator {
        void navigateToChatRoom(User user);
    }
}
