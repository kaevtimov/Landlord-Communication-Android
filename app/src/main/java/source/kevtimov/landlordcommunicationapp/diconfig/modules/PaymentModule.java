package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.payment.ContractsPayments;
import source.kevtimov.landlordcommunicationapp.views.login.payment.PaymentFragment;
import source.kevtimov.landlordcommunicationapp.views.login.payment.PaymentsPresenter;

@Module
public abstract class PaymentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PaymentFragment paymentFragment();

    @ActivityScoped
    @Binds
    abstract ContractsPayments.Presenter paymentPresenter(PaymentsPresenter myPlacesPresenter);
}
