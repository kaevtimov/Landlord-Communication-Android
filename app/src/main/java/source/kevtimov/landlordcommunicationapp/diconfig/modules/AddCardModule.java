package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;
import source.kevtimov.landlordcommunicationapp.views.login.addcard.AddCardFragment;
import source.kevtimov.landlordcommunicationapp.views.login.addcard.AddCardPresenter;
import source.kevtimov.landlordcommunicationapp.views.login.addcard.ContractsAddCard;

@Module
public abstract class AddCardModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddCardFragment addCardFragment();

    @ActivityScoped
    @Binds
    abstract ContractsAddCard.Presenter addCardPresenter(AddCardPresenter addCardPresenter);
}