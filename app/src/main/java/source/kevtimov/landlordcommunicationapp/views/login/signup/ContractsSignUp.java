package source.kevtimov.landlordcommunicationapp.views.login.signup;

import android.os.Bundle;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsSignUp {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void proceedToFinish(Bundle data);

        void setBundleWithUserInfo(Bundle userInfo);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void allowNavigationToFinish(Bundle userData);
    }

    interface Navigator {

        void navigateToFinishSignUp(Bundle userInformation);
    }
}
