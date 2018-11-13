package source.kevtimov.landlordcommunicationapp.views.login.home;

import android.annotation.SuppressLint;

import org.joda.time.LocalDateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.PlaceService;
import source.kevtimov.landlordcommunicationapp.services.base.RentService;
import source.kevtimov.landlordcommunicationapp.services.base.UserService;

public class HomePresenter implements ContractsHome.Presenter {

    private ContractsHome.View mView;
    private SchedulerProvider mSchedulerProvider;
    private RentService mRentService;
    private PlaceService mPlaceService;
    private User mUser;


    @Inject
    public HomePresenter(SchedulerProvider schedulerProvider, RentService rentService, PlaceService placeService) {
        this.mSchedulerProvider = schedulerProvider;
        this.mRentService = rentService;
        this.mPlaceService = placeService;
    }

    @Override
    public void subscribe(ContractsHome.View view) {
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
    public void manageNotifications() {

        if(!mUser.isLandlord()){
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<List<Place>>) emitter -> {
                        List<Place> places = mPlaceService.getAllPlacesByUserId(mUser.getUserId());
                        emitter.onNext(places);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(this::getRentsByPlace,
                            error -> mView.showError(error));

        }
    }

    @Override
    public void allowNavigationToChats() {
        mView.navigateToChats();
    }

    @Override
    public void allowNavigationToPayments() {
        mView.navigateToPayments();
    }

    @Override
    public void allowNavigationToUsers() {
        mView.navigateToUsers();
    }

    @Override
    public void allowNavigationToPlaces() {
        mView.navigateToPlaces();
    }

    private void getRentsByPlace(List<Place> places) {

        for (Place pl : places) {
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<Rent>) emitter -> {
                        Rent rent = mRentService.getRentByPlaceId(pl.getPlaceID());
                        emitter.onNext(rent);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(this::manageRent,
                            error -> mView.showError(error));
        }
    }

    private void manageRent(Rent rent) throws ParseException {

        @SuppressLint("SimpleDateFormat") Date rentDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(rent.getDueDate());
        LocalDateTime currentLocalDateTime = LocalDateTime.parse(rent.getDueDate());
        LocalDateTime beforeFiveDays = currentLocalDateTime.minusDays(5);

        // this is the date 5 days before rent due date
        Date dateForNotification = beforeFiveDays.toDate();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(dateForNotification);

        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        mView.sendNotification(year, month, day, rent);
    }
}
