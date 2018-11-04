package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.AddCardModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.AddPlaceModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.HomeModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.LogInModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.MyPaymentsModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.MyPlacesModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.MyUsersModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PaymentModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PlaceDetailsModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.PlaceManagementModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.SelectPlaceModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.SelectTenantModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.SettingsModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.SignUpModule;
import source.kevtimov.landlordcommunicationapp.diconfig.modules.UserDetailsModule;
import source.kevtimov.landlordcommunicationapp.views.login.addcard.AddCardActivity;
import source.kevtimov.landlordcommunicationapp.views.login.addplace.AddPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.home.HomeActivity;
import source.kevtimov.landlordcommunicationapp.views.login.login.LoginActivity;
import source.kevtimov.landlordcommunicationapp.views.login.mypayments.MyPaymentsActivity;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesActivity;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.MyUsersActivity;
import source.kevtimov.landlordcommunicationapp.views.login.payment.PaymentActivity;
import source.kevtimov.landlordcommunicationapp.views.login.placedetails.PlaceDetailsActivity;
import source.kevtimov.landlordcommunicationapp.views.login.placemanagement.PlaceManagementActivity;
import source.kevtimov.landlordcommunicationapp.views.login.preferences.SettingsActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selectplace.SelectPlaceActivity;
import source.kevtimov.landlordcommunicationapp.views.login.selecttenant.SelectTenantActivity;
import source.kevtimov.landlordcommunicationapp.views.login.signup.SignUpActivity;
import source.kevtimov.landlordcommunicationapp.views.login.userdetails.UserDetailsActivity;

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

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = MyPlacesModule.class
    )
    abstract MyPlacesActivity myPlacesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = PlaceDetailsModule.class
    )
    abstract PlaceDetailsActivity placeDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = PaymentModule.class
    )
    abstract PaymentActivity paymentActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = AddCardModule.class
    )
    abstract AddCardActivity addCardActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = MyPaymentsModule.class
    )
    abstract MyPaymentsActivity myPaymentsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = SettingsModule.class
    )
    abstract SettingsActivity settingsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = MyUsersModule.class
    )
    abstract MyUsersActivity myUsersActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = UserDetailsModule.class
    )
    abstract UserDetailsActivity UserDetailsActivity();
}
