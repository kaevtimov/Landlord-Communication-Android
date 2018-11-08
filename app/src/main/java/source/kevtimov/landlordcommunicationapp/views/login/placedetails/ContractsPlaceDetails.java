package source.kevtimov.landlordcommunicationapp.views.login.placedetails;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsPlaceDetails {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void showEmptyDetails();

        void navigateToPayRent(Place place);

        void manageViewsWithTenant(User notLoggedInUser, User loggedInUser, Place place);

        void manageViewsWithNoTenant(User user, Place place);

        void viewRent(Rent rent);

        void viewEmptyRent();
    }

    interface Presenter {

        void subscribe(View view);

        void setUser(User user);

        void setPlace(Place place);

        void unsubscribe();

        void allowNavigationToPayRent();

        void getNotLogInUser();

        void getUnpaidRent();

        void setRent(Rent rent);

        void editRentAmount(double enteredAmount);
    }

    interface Navigator {

        void navigateToPayRent(Place place);
    }
}
