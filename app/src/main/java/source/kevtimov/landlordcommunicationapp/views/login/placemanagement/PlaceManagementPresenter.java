package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.services.PlaceService;
import source.kevtimov.landlordcommunicationapp.services.RentService;

public class PlaceManagementPresenter implements ContractsPlaceManagement.Presenter {

    private ContractsPlaceManagement.View mView;
    private PlaceService mPlaceService;
    private RentService mRentService;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public PlaceManagementPresenter(RentService rentService, PlaceService placeService, SchedulerProvider schedulerProvider){
        this.mPlaceService = placeService;
        this.mRentService = rentService;
        this.mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(ContractsPlaceManagement.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }


    @Override
    public void registerPlace(Place place) {

        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<Place>) emitter -> {
                    Place placeResponse = mPlaceService.registerPlace(place);
                    emitter.onNext(placeResponse);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(pl -> mView.registerRent(pl.getPlaceID()),
                        error -> {
                            if (error instanceof NullPointerException) {
                                mView.addPlaceFail();
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void registerRent(Rent rent) {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rent>) emitter -> {
                    Rent rentResponse = mRentService.registerRent(rent);
                    emitter.onNext(rentResponse);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(ren-> {},
                        error -> {
                            if (error instanceof NullPointerException) {
                                // if there is null objects
                            } else {
                                mView.showError(error);
                            }
                        });
    }


    @Override
    public void allowToHomeActivity() {
        mView.navigateUserToHome();
    }

    @Override
    public void allowNavigationToAddPlace() {
        mView.navigateUserToAddPlace();
    }

    @Override
    public void allowNavigationToSelectPlace() {
        mView.navigateUserToSelectPlace();
    }


}
