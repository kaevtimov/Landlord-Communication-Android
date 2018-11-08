package source.kevtimov.landlordcommunicationapp.views.login.myplaces;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsMyPlaces {


    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void showPlaces(List<Place> places);

        void showEmptyList();

        void navigateToDetails(Place place);

        void navigateToManagePlace();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadPlaces();

        void setUser(User user);

        void allowNavigationToDetails(Place place);

        void allowNavigationToManagePlaces();
    }

    interface Navigator {

        void navigateToPlaceDetails(Place place);

        void navigateToManagePlace();
    }
}