package source.kevtimov.landlordcommunicationapp.views.login.login;

import android.os.Bundle;

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

        void facebookRegisterAlert();

        void proceedToSignUp();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void checkLogin(String username, String password);

        void setUser(User user);

        void verifyFacebookLogin(String facebookUsername);

        void allowSignUp();
    }

    interface Navigator{
        void navigateToSignUp(Bundle facebookBundle);

        void navigateToHome(User user);
    }
}
