package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.chat.chatview.ChatFragment;
import source.kevtimov.landlordcommunicationapp.chat.chatview.ChatPresenter;
import source.kevtimov.landlordcommunicationapp.chat.chatview.ContractsChat;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;

@Module
public abstract class MessageModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChatFragment chatFragment();

    @ActivityScoped
    @Binds
    abstract ContractsChat.Presenter chatPresenter(ChatPresenter chatSessionPresenter);
}
