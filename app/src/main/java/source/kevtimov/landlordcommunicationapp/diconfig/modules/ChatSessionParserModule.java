package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class ChatSessionParserModule {

    @Provides
    public JsonParser<ChatSession> chatJsonParser() {
        return new GsonJsonParser<ChatSession>(ChatSession.class, ChatSession[].class);
    }
}
