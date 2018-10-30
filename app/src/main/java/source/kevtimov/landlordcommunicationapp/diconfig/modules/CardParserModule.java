package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class CardParserModule {

    @Provides
    public JsonParser<Card> cardJsonParser() {
        return new GsonJsonParser<Card>(Card.class,Card[].class);
    }
}
