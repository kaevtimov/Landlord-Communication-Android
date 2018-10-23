package source.kevtimov.landlordcommunicationapp.views.login;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsLogin {

    interface View {

        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void setNavigator(Navigator navigator);

        void showError(Throwable error);

        void cancelLogin();

        void welcomeUser(User user);

        void alertUser();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void checkLogin(String username, String password);

        void setUser(User user);
    }

    interface Navigator{
        void navigateWith(User user);
    }
}
