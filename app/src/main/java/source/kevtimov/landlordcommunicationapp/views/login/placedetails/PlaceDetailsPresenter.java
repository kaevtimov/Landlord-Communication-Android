package source.kevtimov.landlordcommunicationapp.views.login.placedetails;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class PlaceDetailsPresenter implements ContractsPlaceDetails.Presenter {

    private ContractsPlaceDetails.View mView;
    private UserService mUserService;
    private SchedulerProvider mSchedulerProvider;
    private User mUser;
    private Place mPlace;

    @Inject
    public PlaceDetailsPresenter(UserService userService, SchedulerProvider schedulerProvider){
        this.mSchedulerProvider = schedulerProvider;
        this.mUserService = userService;
    }

    @Override
    public void subscribe(ContractsPlaceDetails.View view) {
        this.mView = view;
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setPlace(Place place) {
        this.mPlace = place;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void allowNavigationToPayRent() {
        mView.navigateToPayRent(mPlace);
    }

    @Override
    public void getNotLogInUser() {
        if(mUser.isLandlord()){
            if(mPlace.getTenantID() == 0){
                mView.manageViewsWithNoTenant(mUser, mPlace);
            }else{
                getNotLogInUser(mPlace.getTenantID());
            }
        }else{
            getNotLogInUser(mPlace.getLandlordID());
        }
    }


    private void getNotLogInUser(int userId) {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mUserService.getUserById(userId);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(u -> mView.manageViewsWithTenant(u, mUser, mPlace)
                        , error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                                mView.showEmptyDetails();
                            } else {
                                mView.showError(error);
                            }
                        });
    }
}
