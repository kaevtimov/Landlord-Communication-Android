package source.kevtimov.landlordcommunicationapp.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.SqlUserRepository;
import source.kevtimov.landlordcommunicationapp.repositories.UserRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class UserRepositoryModule {

    @Provides
    @Singleton
    public UserRepository userRepository(HttpRequester requester, JsonParser<User> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/users";
        return new SqlUserRepository(url,requester,parser);
    }
}
