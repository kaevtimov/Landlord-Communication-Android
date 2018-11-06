package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class ParserModule {

    @Provides
    public JsonParser<User> userJsonParser() {
        return new GsonJsonParser<User>(User.class,User[].class);
    }

    @Provides
    public JsonParser<Message> messageJsonParser() {
        return new GsonJsonParser<Message>(Message.class,Message[].class);
    }
}
