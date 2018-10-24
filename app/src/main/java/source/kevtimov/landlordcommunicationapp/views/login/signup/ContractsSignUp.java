package source.kevtimov.landlordcommunicationapp.views.login.signup;

import android.os.Bundle;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsSignUp {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void proceedToPlaceManagement(User user);

        void setBundleWithUserInfo(Bundle userInfo);

        void showLoading();

        void hideLoading();

        void signUpFail();

        void showError(Throwable error);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void registerUser(Bundle userData);
    }

    interface Navigator {

        void navigateToPlaceManagement(User user);
    }
}
