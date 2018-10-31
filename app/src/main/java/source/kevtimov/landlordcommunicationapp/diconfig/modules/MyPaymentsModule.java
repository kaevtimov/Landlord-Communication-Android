package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.mypayments.ContractsMyPayments;
import source.kevtimov.landlordcommunicationapp.views.login.mypayments.MyPaymentsFragment;
import source.kevtimov.landlordcommunicationapp.views.login.mypayments.MyPaymentsPresenter;

@Module
public abstract class MyPaymentsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyPaymentsFragment myPaymentsFragment();

    @ActivityScoped
    @Binds
    abstract ContractsMyPayments.Presenter myPaymentsPresenter(MyPaymentsPresenter myPaymentsPresenter);
}
