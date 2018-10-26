package source.kevtimov.landlordcommunicationapp.views.login.addplace;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsAddPlace {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();
    }

    interface Navigator {

        void navigateToHome(User user);
    }
}
