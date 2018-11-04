package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class RatingParserModule {

    @Provides
    public JsonParser<Rating> ratingJsonParser() {
        return new GsonJsonParser<Rating>(Rating.class, Rating[].class);
    }
}
