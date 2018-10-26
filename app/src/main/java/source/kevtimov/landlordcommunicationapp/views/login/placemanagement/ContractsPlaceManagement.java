package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.os.Bundle;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsPlaceManagement {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void showPlaces(List<Place> places);

        void showEmptyList();

        void fillPlacesList(Place place);

        void fillRentsList(Rent rent);

        void setUser(User user);

        void NavigateUserToHome();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadPlaces(List<Place> places);

        void registerPlaces(List<Place> places);

        void registerRents(List<Rent> rents);

        void allowNavigation();
    }

    interface Navigator {

        void navigateToHomeActivity(User userInfo);
    }
}
