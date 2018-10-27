package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class PlaceParserModule {

    @Provides
    public JsonParser<Place> placeJsonParser() {
        return new GsonJsonParser<Place>(Place.class,Place[].class);
    }
}
