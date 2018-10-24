package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class PlaceManagementPresenter implements ContractsPlaceManagement.Presenter {

    private ContractsPlaceManagement.View mView;
    private UserService mService;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public PlaceManagementPresenter(UserService userService, SchedulerProvider schedulerProvider){
        this.mService = userService;
        this.mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(ContractsPlaceManagement.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadPlaces(List<Place> places) {

        if (places.isEmpty()) {
            mView.showEmptyList();
        } else {
            mView.showPlaces(places);
        }
    }
}
