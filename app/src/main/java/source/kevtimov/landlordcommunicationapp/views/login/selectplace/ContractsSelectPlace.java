package source.kevtimov.landlordcommunicationapp.views.login.selectplace;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsSelectPlace {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void navigateToPlaceManagement(ArrayList<Place> places);

        void showEmptyList();

        void showPlaces(List<Place> places);
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void navigateBackToPlaceManagement(ArrayList<Place> mArrayPlaces);

        void getAllPlacesWhereNoTenant();
    }

    interface Navigator {

        void navigateToPlaceManagementActivity(ArrayList<Place> places);
    }
}