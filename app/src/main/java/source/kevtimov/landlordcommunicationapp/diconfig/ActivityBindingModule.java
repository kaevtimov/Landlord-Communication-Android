package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.chat.ChatActivity;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomActivity;
import source.kevtimov.landlordcommunicationapp.views.login.LoginActivity;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
            modules = LogInModule.class
    )
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = ChatModule.class
    )
    abstract ChatActivity chatActivity();

    @ActivityScoped
    @ContributesAndroidInjector(
            modules = ChatRoomModule.class
    )
    abstract ChatRoomActivity chatRoomActivity();
}
