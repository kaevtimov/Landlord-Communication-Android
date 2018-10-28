package source.kevtimov.landlordcommunicationapp.chat;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ChatContracts {
    interface Presenter {
        void selectLandlord(User landlord);

        void subscribe(View view);

        void loadLandlords();
    }

    interface View {
        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLandlords(List<User> landlordsList);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();

        void showEmptyLandlordList();
    }

    interface Navigator {
        void navigateToChatRoom(User user);
    }
}
