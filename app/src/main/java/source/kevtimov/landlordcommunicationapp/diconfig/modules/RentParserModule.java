package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class RentParserModule {

    @Provides
    public JsonParser<Rent> rentJsonParser() {
        return new GsonJsonParser<Rent>(Rent.class,Rent[].class);
    }
}