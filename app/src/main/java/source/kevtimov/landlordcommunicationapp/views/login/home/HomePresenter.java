package source.kevtimov.landlordcommunicationapp.views.login.home;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class HomePresenter implements ContractsHome.Presenter {

    private ContractsHome.View mView;
    private SchedulerProvider mSchedulerProvider;
    private UserService mUserService;


    @Inject
    public HomePresenter(UserService userService, SchedulerProvider schedulerProvider){
        this.mUserService = userService;
        this.mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(ContractsHome.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
