package source.kevtimov.landlordcommunicationapp.views.login.login;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class LoginPresenter implements ContractsLogin.Presenter {

    private ContractsLogin.View mView;
    private SchedulerProvider mSchedulerProvider;
    private UserService mService;
    private User mUser;

    @Inject
    public LoginPresenter(SchedulerProvider provider, UserService service) {
        this.mSchedulerProvider = provider;
        this.mService = service;
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
                    .doFinally(mView::hideLoading)
                    .subscribe(user -> processCustomUser(username),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    mView.cancelLogin();
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void verifyFacebookLogin(String username) {
        //check if username exists
        checkFacebookUserByUsername(username);

        if(mUser != null){
            mView.welcomeUser(mUser);
        }
    }

    @Override
    public void allowSignUp() {
        mView.proceedToSignUp();
    }

    private void checkFacebookUserByUsername(String username) {

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
                .subscribe(this::setUser
                        , error -> {
                            if (error instanceof NullPointerException) {
                                mView.facebookRegisterAlert();
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    private void processCustomUser(String username) {
        checkCustomUserByUsername(username);
        if (mUser != null) {
            mView.welcomeUser(mUser);
        }
    }

    private void checkCustomUserByUsername(String username) {

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
                .subscribe(this::setUser
                        , error -> mView.showError(error));
    }
}
