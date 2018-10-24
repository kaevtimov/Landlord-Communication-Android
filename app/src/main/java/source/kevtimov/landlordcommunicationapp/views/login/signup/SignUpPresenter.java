package source.kevtimov.landlordcommunicationapp.views.login.signup;


import android.os.Bundle;

import javax.inject.Inject;


public class SignUpPresenter implements ContractsSignUp.Presenter{


    private ContractsSignUp.View mView;

    @Inject
    public SignUpPresenter() {

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
    public void allowNavigationToFinish(Bundle userData) {
        mView.proceedToFinish(userData);
    }
}
