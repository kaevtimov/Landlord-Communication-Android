package source.kevtimov.landlordcommunicationapp.views.login.myusers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.PlaceService;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class MyUsersPresenter implements ContractsMyUsers.Presenter {

    private ContractsMyUsers.View mView;
    private PlaceService mPlaceService;
    private UserService mUserService;
    private SchedulerProvider mSchedulerProvider;
    private User mUser;


    @Inject
    public MyUsersPresenter(SchedulerProvider schedulerProvider, PlaceService placeService, UserService userService){
        this.mSchedulerProvider = schedulerProvider;
        this.mPlaceService = placeService;
        this.mUserService = userService;
    }


    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void subscribe(ContractsMyUsers.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }

    @Override
    public void loadUsers() {
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
                .subscribe(this::loadOtherUsers,
                        error -> mView.showError(error));
    }

    //these are incoming places for logged in user
    private void loadOtherUsers(List<Place> places) {
        if(mUser.isLandlord()){
            for (Place place:places) {
                Disposable observal = Observable
                        .create((ObservableOnSubscribe<User>) emitter -> {
                            User user = mUserService.getUserById(place.getTenantID());
                            emitter.onNext(user);
                            emitter.onComplete();
                        })
                        .subscribeOn(mSchedulerProvider.background())
                        .observeOn(mSchedulerProvider.ui())
                        .doFinally(mView::hideLoading)
                        .subscribe(mView::addUser,
                                error -> mView.showError(error));
            }
        }else{
            for (Place place:places) {
                Disposable observal = Observable
                        .create((ObservableOnSubscribe<User>) emitter -> {
                            User user = mUserService.getUserById(place.getLandlordID());
                            emitter.onNext(user);
                            emitter.onComplete();
                        })
                        .subscribeOn(mSchedulerProvider.background())
                        .observeOn(mSchedulerProvider.ui())
                        .doFinally(mView::hideLoading)
                        .subscribe(mView::addUser,
                                error -> mView.showError(error));
            }
        }
    }
}
