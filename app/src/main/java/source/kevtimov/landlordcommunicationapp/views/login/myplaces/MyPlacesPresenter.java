package source.kevtimov.landlordcommunicationapp.views.login.myplaces;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.PlaceService;

public class MyPlacesPresenter implements ContractsMyPlaces.Presenter {

    private ContractsMyPlaces.View mView;
    private PlaceService mPlaceService;
    private SchedulerProvider mSchedulerProvider;
    private User mUser;

    @Inject
    public MyPlacesPresenter(PlaceService placeService, SchedulerProvider schedulerProvider){
        this.mPlaceService = placeService;
        this.mSchedulerProvider = schedulerProvider;
    }


    @Override
    public void subscribe(ContractsMyPlaces.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadPlaces() {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<List<Place>>) emitter -> {
                    List<Place> places = mPlaceService.getAllPlacesByUserId(mUser.getUserId());
                    emitter.onNext(places);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::viewPlaces,
                        error -> mView.showError(error));
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void allowNavigationToDetails(Place place) {
        mView.navigateToDetails(place);
    }

    @Override
    public void allowNavigationToManagePlaces() {
        mView.navigateToManagePlace();
    }

    private void viewPlaces(List<Place> places) {
        if (places.isEmpty()) {
            mView.showEmptyList();
        } else {
            mView.showPlaces(places);
        }
    }
}
