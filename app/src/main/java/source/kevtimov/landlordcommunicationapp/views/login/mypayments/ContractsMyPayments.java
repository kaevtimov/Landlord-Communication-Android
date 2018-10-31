package source.kevtimov.landlordcommunicationapp.views.login.mypayments;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsMyPayments {

    interface View {

        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void showPayments(List<Payment> payments);

        void showEmptyList();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadPayments();

        void setUser(User user);
    }
}
