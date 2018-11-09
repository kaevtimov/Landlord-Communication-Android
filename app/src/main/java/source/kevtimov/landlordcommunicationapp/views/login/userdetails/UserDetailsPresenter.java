package source.kevtimov.landlordcommunicationapp.views.login.userdetails;

import android.graphics.Bitmap;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.PlaceService;
import source.kevtimov.landlordcommunicationapp.services.base.RatingService;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcache.BitmapCache;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;

public class UserDetailsPresenter implements ContractsUserDetails.Presenter {

    private ContractsUserDetails.View mView;
    private SchedulerProvider mSchedulerProvider;
    private RatingService mRatingService;
    private PlaceService mPlaceService;
    private User mOtherUser;
    private User mLoggedInUser;
    private BitmapCache mBitmapCache;
    private IBitmapAgent mBitmapAgent;

    @Inject
    public UserDetailsPresenter(PlaceService placeService, SchedulerProvider schedulerProvider, RatingService ratingService, IBitmapAgent agent){
        this.mSchedulerProvider = schedulerProvider;
        this.mPlaceService = placeService;
        this.mRatingService = ratingService;
        this.mBitmapCache = BitmapCache.getInstance();
        this.mBitmapAgent = agent;
    }

    @Override
    public void subscribe(ContractsUserDetails.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setOtherUser(User user) {
        this.mOtherUser = user;
    }

    @Override
    public void setLoggedInUser(User mUser) {
        this.mLoggedInUser = mUser;
    }

    @Override
    public void loadPlaces() {

        if(mLoggedInUser.isLandlord()){
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<List<Place>>) emitter -> {
                        List<Place> commonPlaces = mPlaceService.getAllByTenantIdAndLandlordId(mOtherUser.getUserId(), mLoggedInUser.getUserId());
                        emitter.onNext(commonPlaces);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(mView::showPlaces
                            , error -> {
                                if (error instanceof NullPointerException) {
                                    // in case of null pointer exception
                                } else {
                                    mView.showError(error);
                                }
                            });
        } else{
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<List<Place>>) emitter -> {
                        List<Place> commonPlaces = mPlaceService.getAllByTenantIdAndLandlordId(mLoggedInUser.getUserId(), mOtherUser.getUserId());
                        emitter.onNext(commonPlaces);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(mView::showPlaces
                            , error -> {
                                if (error instanceof NullPointerException) {
                                    // in case of null pointer exception
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
    }

    @Override
    public void getRating() {
        mView.showLoading();

        Disposable observal = Observable
                .create((ObservableOnSubscribe<List<Rating>>) emitter -> {
                    List<Rating> ratings = mRatingService.getAllByUserId(mOtherUser.getUserId());
                    emitter.onNext(ratings);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::manageRatings
                        , error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void checkRating(double rating) {

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating check = mRatingService.checkRating(mOtherUser.getUserId(), mLoggedInUser.getUserId());
                    emitter.onNext(check);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(rat -> addRating(rating)
                        , error -> {
                            if (error instanceof NullPointerException) {
                                mView.alertAlreadyVoted();
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void loadUser() {
        convertStringToBitmap();
        mView.showUser(mOtherUser);
    }

    private void addRating(double rating) {

        Rating sendingRating = new Rating(mOtherUser.getUserId(), mLoggedInUser.getUserId(), rating);

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rating>) emitter -> {
                    Rating ratingIncome = mRatingService.addRating(sendingRating);
                    emitter.onNext(ratingIncome);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(mView::showInfo
                        , error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }


    private void convertStringToBitmap() {
        String picture = mOtherUser.getPicture();
        if(picture != null){
            Bitmap bitmap = mBitmapAgent.convertStringToBitmap(picture);
            setBitmapToCache(bitmap);
        }
    }

    private void setBitmapToCache(Bitmap bitmap) {
        if(mBitmapCache.getLruCache().get(mOtherUser.getUsername() + "_profile_image") == null){
            mBitmapCache.getLruCache().put(mOtherUser.getUsername() + "_profile_image", bitmap);
        }
    }

    private void manageRatings(List<Rating> ratings) {
        double rating = 0;

        if(ratings.size() > 0){
            for (Rating rat:ratings) {
                rating += rat.getRating();
            }
        }
        mView.showRating(rating/ratings.size());
    }
}
