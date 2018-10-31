package source.kevtimov.landlordcommunicationapp.views.login.payment;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsPayments {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void manageCardView(List<Card> cards);

        void manageRentView(Rent rent);

        void alertNotEnoughMoney();

        void alertEnteredAmountBiggerThanRemaining();

        void navigateToDetailsActivity();

        void navigateToCardAdd();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void setUser(User user);

        void setPlace(Place place);

        void setRent(Rent rent);

        void setCard(Card card);

        void loadRent();

        void loadCards();

        void managePayment(double enteredAmount);

        void allowNavigateToCardAdd();
    }

    interface Navigator {

        void navigateToMyPlacesActivity();

        void navigateToAddCard();
    }
}
