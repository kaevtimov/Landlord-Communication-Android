package source.kevtimov.landlordcommunicationapp.views.login.signup;


import android.os.Bundle;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.UserService;


public class SignUpPresenter implements ContractsSignUp.Presenter {


    private ContractsSignUp.View mView;
    private UserService mUserService;
    private SchedulerProvider mSchedulerProvider;


    @Inject
    public SignUpPresenter(UserService service, SchedulerProvider provider) {
        this.mSchedulerProvider = provider;
        this.mUserService = service;
    }

    @Override
    public void subscribe(ContractsSignUp.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void registerUser(Bundle userData) {

        if (Objects.equals(userData.get("Purpose"), "facebook")) {
            String username = userData.getString("fb_username");
            String firstName = userData.getString("fb_first_name");
            String lastName = userData.getString("fb_last_name");
            String email = userData.getString("fb_email");
            String password = null;
            boolean isOnline = true;
            boolean isLandlord = userData.getBoolean("isLandlord");
            String picture = userData.getString("fb_prof_pic");

            //register fb user
            User fbUser = new User(isLandlord, username, picture, firstName, lastName, email,
                    isOnline, null, null);

            mView.showLoading();

            Disposable observal = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User user = mUserService.registerUser(fbUser, password, "facebook");
                        emitter.onNext(user);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(user -> mView.proceedToPlaceManagement(user),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    mView.signUpFail();
                                } else {
                                    mView.showError(error);
                                }
                            });

        } else {
            String username = userData.getString("username");
            String firstName = userData.getString("first_name");
            String lastName = userData.getString("last_name");
            String email = userData.getString("email");
            String password = userData.getString("password");
            boolean isOnline = true;
            boolean isLandlord = userData.getBoolean("isLandlord");
            String picture = null;

            //register custom user
            User customUser = new User(isLandlord, username, picture, firstName, lastName, email,
                    isOnline, null, null);

            mView.showLoading();

            Disposable observal = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User user = mUserService.registerUser(customUser, password, "custom");
                        emitter.onNext(user);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(user -> mView.proceedToPlaceManagement(user),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    mView.signUpFail();
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
    }

    @Override
    public void checkUsernameAndEmail(String username, String email) {

        mView.showLoading();

        Disposable observal = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User response = mUserService.checkUsernameAndEmail(username, email);
                    emitter.onNext(response);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(user -> mView.processCheckResult(user),
                        error -> mView.showError(error));
    }
}
