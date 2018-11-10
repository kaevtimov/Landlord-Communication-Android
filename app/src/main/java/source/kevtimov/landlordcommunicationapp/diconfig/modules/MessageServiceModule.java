package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.base.MessageRepository;
import source.kevtimov.landlordcommunicationapp.services.base.MessageService;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpMessageService;

@Module
public class MessageServiceModule {

    @Provides
    public MessageService getService(MessageRepository repo){
        return new HttpMessageService(repo);
    }
}
