package source.kevtimov.landlordcommunicationapp.views.login.addcard;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsAddCard {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void navigateBack();

        void alertForConstraints();

        void alertEmptyFields();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUser(User user);

        void addCard(String cardType, String cardBrand, String cardNumber, String cvvNumber);
    }

    interface Navigator {
        void navigateBack();
    }
}
