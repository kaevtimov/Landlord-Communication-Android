package source.kevtimov.landlordcommunicationapp.views.login.selectplace;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.services.PlaceService;

public class SelectPlacePresenter implements ContractsSelectPlace.Presenter {

    private ContractsSelectPlace.View mView;
    private PlaceService mPlaceService;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public SelectPlacePresenter(PlaceService placeService, SchedulerProvider schedulerProvider) {
        this.mPlaceService = placeService;
        this.mSchedulerProvider = schedulerProvider;
    }


    @Override
    public void subscribe(ContractsSelectPlace.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }

    @Override
    public void navigateBackToPlaceManagement(ArrayList<Place> mArrayPlaces) {
        mView.navigateToPlaceManagement(mArrayPlaces);
    }

    @Override
    public void getAllPlacesWhereNoTenant() {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<List<Place>>) emitter -> {
                    List<Place> places = mPlaceService.getAllPlacesWithNoTenants();
                    emitter.onNext(places);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::viewPlaces,
                        error -> mView.showError(error));
    }

    private void viewPlaces(List<Place> places) {
        if (places.isEmpty()) {
            mView.showEmptyList();
        } else {
            mView.showPlaces(places);
        }
    }
}
