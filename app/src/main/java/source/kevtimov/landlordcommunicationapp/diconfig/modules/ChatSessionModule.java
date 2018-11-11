package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.chat.sessions.ChatSessionFragment;
import source.kevtimov.landlordcommunicationapp.chat.sessions.ChatSessionPresenter;
import source.kevtimov.landlordcommunicationapp.chat.sessions.ContractsChatSession;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;

@Module
public abstract class ChatSessionModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChatSessionFragment chatFragment();

    @ActivityScoped
    @Binds
    abstract ContractsChatSession.Presenter chatPresenter(ChatSessionPresenter chatSessionPresenter);
}
