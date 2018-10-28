package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.AddPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeActivity;
import source.kevtimov.landlordcommunicationapp.views.login.login.LoginActivity;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.SelectPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantActivity;
import source.kevtimov.landlordcommunicationapp.views.login.signup.SignUpActivity;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
            modules = LogInModule.class
    )
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SignUpModule.class
    )
    abstract SignUpActivity signUpActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = PlaceManagementModule.class
    )
    abstract PlaceManagementActivity placeManagementActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = AddPlaceModule.class
    )
    abstract AddPlaceActivity addPlaceActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SelectTenantModule.class
    )
    abstract SelectTenantActivity selectTenantActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SelectPlaceModule.class
    )
    abstract SelectPlaceActivity selectPlaceActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = HomeModule.class
    )
    abstract HomeActivity homeActivity();
}
