package source.kevtimov.landlordcommunicationapp.views.login;

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
    public LoginPresenter(SchedulerProvider provider, UserService service){
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

        if (password.equals("asd123") && username.equals("cwillmontq")) {
            processUser(username);
        }

        /*if(password.length() == 0 || username.length() == 0){
            mView.hideLoading();
            mView.alertUser();
        }else{
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<User>) emitter -> {
                        User user = mService.checkUserLogin(username, password);
                        emitter.onNext(user);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(user -> processUser(username),
                            error -> {
                                if(error instanceof NullPointerException){
                                    mView.cancelLogin();
                                }else{
                                    mView.showError(error);
                                }
                            });
        }*/
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }


    private void processUser(String username) {

        getUserByUsername(username);
/*
        if(mUser != null){
            mView.welcomeUser(mUser);
        }*/
    }

    private void getUserByUsername(String username) {
        Disposable observal = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User user = mService.getUserByUsername(username);
                    emitter.onNext(user);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(mView::welcomeUser
                        ,error -> mView.showError(error));
    }
}
