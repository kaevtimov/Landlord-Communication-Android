package source.kevtimov.landlordcommunicationapp.views.login.placedetails;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.RentService;
import source.kevtimov.landlordcommunicationapp.services.base.UserService;

public class PlaceDetailsPresenter implements ContractsPlaceDetails.Presenter {

    private ContractsPlaceDetails.View mView;
    private UserService mUserService;
    private RentService mRentService;
    private SchedulerProvider mSchedulerProvider;
    private User mUser;
    private Rent mRent;
    private Place mPlace;

    @Inject
    public PlaceDetailsPresenter(UserService userService, SchedulerProvider schedulerProvider, RentService rentService){
        this.mSchedulerProvider = schedulerProvider;
        this.mUserService = userService;
        this.mRentService = rentService;
    }

    @Override
    public void subscribe(ContractsPlaceDetails.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
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
    public void setRent(Rent rent) {
        this.mRent = rent;
    }

    @Override
    public void editRentAmount(String amount) {


        if(amount.length() == 0){
            mView.alertForBlankAmountInfo();
        } else if(Double.parseDouble(amount) < 50 || Double.parseDouble(amount) > 9000){
            mView.alertForAmountConstraint();
        } else{
            Rent rent = mRent;
            double enteredAmount = Double.parseDouble(amount);
            rent.setTotalAmount(enteredAmount);
            rent.setRemainingAmount(enteredAmount);

            Disposable observal = Observable
                    .create((ObservableOnSubscribe<Rent>) emitter -> {
                        Rent rentEdit = mRentService.editRent(rent, mRent.getRentID());
                        emitter.onNext(rentEdit);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(mView::viewRent,
                            error -> {
                                if (error instanceof NullPointerException) {
                                    // in case of null pointer exception
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
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

    @Override
    public void getUnpaidRent() {
        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rent>) emitter -> {
                    Rent rent = mRentService.getRentByPlaceId(mPlace.getPlaceID());
                    emitter.onNext(rent);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(rent -> mView.viewRent(rent)
                        , error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                                mView.viewEmptyRent();
                            } else {
                                mView.showError(error);
                            }
                        });
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
