package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import source.kevtimov.landlordcommunicationapp.chat.templatemessage.ContractsTemplateMessage;
import source.kevtimov.landlordcommunicationapp.chat.templatemessage.TemplateMessageFragment;
import source.kevtimov.landlordcommunicationapp.chat.templatemessage.TemplateMessagePresenter;
import source.kevtimov.landlordcommunicationapp.diconfig.ActivityScoped;
import source.kevtimov.landlordcommunicationapp.diconfig.FragmentScoped;

@Module
public abstract class TemplateMessageModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract TemplateMessageFragment templateMessageFragment();

    @ActivityScoped
    @Binds
    abstract ContractsTemplateMessage.Presenter templateMessagePresenter(TemplateMessagePresenter templateMessagePresenter);

}
