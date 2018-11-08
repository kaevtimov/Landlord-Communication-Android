package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.ContractsSelectTenant;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantFragment;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantPresenter;

@Module
public abstract class SelectTenantModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SelectTenantFragment selectTenantFragment();

    @ActivityScoped
    @Binds
    abstract ContractsSelectTenant.Presenter selectTenantPresenter(SelectTenantPresenter selectTenantPresenter);

}
