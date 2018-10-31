package source.kevtimov.landlordcommunicationapp.views.login.mypayments;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.PaymentService;

public class MyPaymentsPresenter implements ContractsMyPayments.Presenter {


    private ContractsMyPayments.View mView;
    private SchedulerProvider mSchedulerProvider;
    private PaymentService mPaymentService;
    private User mUser;


    @Inject
    public MyPaymentsPresenter(SchedulerProvider schedulerProvider, PaymentService paymentService){
        this.mSchedulerProvider = schedulerProvider;
        this.mPaymentService = paymentService;
    }

    @Override
    public void subscribe(ContractsMyPayments.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void loadPayments() {
        if(mUser.isLandlord()){
            mView.showLoading();
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<List<Payment>>) emitter -> {
                        List<Payment> payments = mPaymentService.getAllPaymentsByLandlordId(mUser.getUserId());
                        emitter.onNext(payments);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(this::viewPayments,
                            error -> mView.showError(error));
        } else{
            mView.showLoading();
            Disposable observal = Observable
                    .create((ObservableOnSubscribe<List<Payment>>) emitter -> {
                        List<Payment> payments = mPaymentService.getAllPaymentsByTenantId(mUser.getUserId());
                        emitter.onNext(payments);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .doFinally(mView::hideLoading)
                    .subscribe(this::viewPayments,
                            error -> mView.showError(error));
        }
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    private void viewPayments(List<Payment> payments) {
        if (payments.isEmpty()) {
            mView.showEmptyList();
        } else {
            mView.showPayments(payments);
        }
    }
}
