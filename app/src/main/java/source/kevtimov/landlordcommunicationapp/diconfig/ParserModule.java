package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class ParserModule {

    @Provides
    public JsonParser<User> userJsonParser() {
        return new GsonJsonParser<User>(User.class,User[].class);
    }
}
