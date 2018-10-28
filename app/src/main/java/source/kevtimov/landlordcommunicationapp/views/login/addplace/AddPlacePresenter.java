package source.kevtimov.landlordcommunicationapp.views.login.addplace;

import android.os.Bundle;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.services.PlaceService;
import source.kevtimov.landlordcommunicationapp.services.RentService;

public class AddPlacePresenter implements ContractsAddPlace.Presenter {

    private ContractsAddPlace.View mView;
    private PlaceService mPlaceSeevice;
    private RentService mRentService;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public AddPlacePresenter(SchedulerProvider schedulerProvider, PlaceService placeService, RentService rentService){
        this.mSchedulerProvider = schedulerProvider;
        this.mPlaceSeevice = placeService;
        this.mRentService = rentService;
    }

    @Override
    public void subscribe(ContractsAddPlace.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void allowNavigationOnCancel() {
        mView.navigateToPlaceManagementOnCancel();
    }

    @Override
    public void allowNavigationOnSave(Bundle info) {
        mView.navigateToPlaceManagementOnSave(info);
    }

    @Override
    public void allowNavigateToSelectTenant() {
        mView.navigateToSelectTenant();
    }
}
