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

        void setUser(User user);

        void navigateUserToHome();

        void navigateUserToAddPlace();

        void navigateUserToSelectPlace();

        void addPlaceFail();

        void manageIncomingInformation(Bundle info);

        void registerRent(int placeId);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void registerPlace(Place place);

        void registerRent(Rent rent);

        void allowToHomeActivity();

        void allowNavigationToAddPlace();

        void allowNavigationToSelectPlace();
    }

    interface Navigator {

        void navigateToHomeActivity(User userInfo);

        void navigateToAddPlaceActivity();

        void navigateToSelectPlaceActivity();
    }
}
