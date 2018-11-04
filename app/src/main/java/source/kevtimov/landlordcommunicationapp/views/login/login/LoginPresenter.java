package source.kevtimov.landlordcommunicationapp.views.login.login;

import android.graphics.Bitmap;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.UserService;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcache.BitmapCache;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;

public class LoginPresenter implements ContractsLogin.Presenter {

    private ContractsLogin.View mView;
    private SchedulerProvider mSchedulerProvider;
    private UserService mService;
    private BitmapCache mBitmapCache;
    private IBitmapAgent mBitmapAgent;

    @Inject
    public LoginPresenter(SchedulerProvider provider, UserService service, IBitmapAgent agent) {
        this.mSchedulerProvider = provider;
        this.mService = service;
        this.mBitmapAgent = agent;
        this.mBitmapCache = BitmapCache.getInstance();
    }


    @Override
    public void subscribe(ContractsLogin.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void checkLogin(String username, String password) {

        mView.showLoading();
        if (password.length() == 0 || username.length() == 0) {
            mView.hideLoading();
            mView.alertUserForBlankInfo();
        } else if (password.length() < 6 || username.length() < 6) {
            mView.hideLoading();
            mView.alertUserForLengthConstraints();
        } else {
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User user = mService.checkUserLogin(username, password);
                        emitter.onNext(user);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .subscribe(user -> checkCustomUserByUsername(username),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    mView.hideLoading();
                                    mView.cancelLogin();
                                } else {
                                    mView.hideLoading();
                                    mView.showError(error);
                                }
                            });
        }
    }

    @Override
    public void allowSignUp() {
        mView.proceedToSignUp();
    }

    @Override
    public void checkFacebookUserByUsername(String username) {

        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mService.getUserByUsername(username);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(mView::welcomeUser
                        , error -> {
                            if (error instanceof NullPointerException) {
                                mView.facebookRegisterAlert();
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void setBitmapToCache(String userPicture) {
        Bitmap profPic = mBitmapAgent.convertStringToBitmap(userPicture);
        if(mBitmapCache.getLruCache().get("logged_in_user_profile_image") == null){
            mBitmapCache.getLruCache().put("logged_in_user_profile_image", profPic);
        }
    }


    private void checkCustomUserByUsername(String username) {
        Disposable observal = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mService.getUserByUsername(username);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(mView::welcomeUser
                        , error -> mView.showError(error));
    }
}
