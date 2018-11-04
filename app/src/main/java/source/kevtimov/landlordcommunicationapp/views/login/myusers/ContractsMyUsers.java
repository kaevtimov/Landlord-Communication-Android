package source.kevtimov.landlordcommunicationapp.views.login.myusers;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsMyUsers {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void addUser(User user);

        void navigateToDetails(User user);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadUsers();

        void setUser(User user);

        void allowNavigation(User user);
    }

    interface Navigator {
        void navigateToDetails(User user);
    }
}
