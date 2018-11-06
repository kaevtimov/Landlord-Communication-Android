package source.kevtimov.landlordcommunicationapp.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.UserRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpMessageService;
import source.kevtimov.landlordcommunicationapp.services.HttpUserService;
import source.kevtimov.landlordcommunicationapp.services.MessageService;
import source.kevtimov.landlordcommunicationapp.services.UserService;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;

@Module
public class ServiceModule {

    @Provides
    public UserService getService(UserRepository repo, IPasswordAgent agent){
        return new HttpUserService(repo, agent);
    }

    @Provides
    @Singleton
    public MessageService getMessageService(HttpRequester httpRequester, JsonParser<Message> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/messages";
        return new HttpMessageService(httpRequester,parser,url);
    }


}
