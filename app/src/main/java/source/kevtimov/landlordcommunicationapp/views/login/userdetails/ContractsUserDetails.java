package source.kevtimov.landlordcommunicationapp.views.login.userdetails;

import android.graphics.Bitmap;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.ContractsMyUsers;

public interface ContractsUserDetails {

    interface View {

        void setPresenter(ContractsUserDetails.Presenter presenter);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void showRating(double rating);

        void showUser(User user);

        void alertAlreadyVoted();

        void showInfo(Rating rating);

        void showPlaces(List<Place> places);
    }

    interface Presenter {

        void subscribe(ContractsUserDetails.View view);

        void unsubscribe();

        void loadUser();

        void setOtherUser(User user);

        void getRating();

        void checkRating(double rating);

        void setLoggedInUser(User mUser);

        void loadPlaces();

    }
}
