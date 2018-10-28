package source.kevtimov.landlordcommunicationapp.views.login.selecttenant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class SelectTenantPresenter implements ContractsSelectTenant.Presenter{

    private ContractsSelectTenant.View mView;
    private UserService mUserService;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public SelectTenantPresenter(UserService userService, SchedulerProvider schedulerProvider){
        this.mUserService = userService;
        this.mSchedulerProvider = schedulerProvider;
    }


    @Override
    public void subscribe(ContractsSelectTenant.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadTenants() {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<List<User>>) emitter -> {
                    List<User> tenants = mUserService.getAllTenants();
                    emitter.onNext(tenants);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(this::viewTenants,
                        error -> mView.showError(error));
    }

    @Override
    public void allowNavigateOnCancel() {
        mView.navigateOnCancel();
    }

    @Override
    public void allowNavigateOnDone(User tenant) {
        mView.navigateOnDone(tenant);
    }

    private void viewTenants(List<User> tenants) {
        if (tenants.isEmpty()) {
            mView.showEmptyList();
        } else {
            mView.showTenants(tenants);
        }
    }
}
