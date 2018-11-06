package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomContracts;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomFragment;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomPresenter;

@Module
public abstract class ChatRoomModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ChatRoomFragment chatFragment();

    @ActivityScoped
    @Binds
    abstract ChatRoomContracts.Presenter chatRoomPresenter(ChatRoomPresenter chatPresenter);
}
