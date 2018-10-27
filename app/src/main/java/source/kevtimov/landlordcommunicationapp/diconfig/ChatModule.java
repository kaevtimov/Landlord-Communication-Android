package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.chat.ChatContracts;
import source.kevtimov.landlordcommunicationapp.chat.ChatFragment;

@Module
public abstract class ChatModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChatFragment chatFragment();

    @ActivityScoped
    @Binds
    abstract ChatContracts.Presenter chatPresenter(ChatContracts.Presenter chatPresenter);

}
