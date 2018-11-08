package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.CardRepository;
import source.kevtimov.landlordcommunicationapp.services.CardService;
import source.kevtimov.landlordcommunicationapp.services.HttpCardService;

@Module
public class CardServiceModule {

    @Provides
    public CardService getService(CardRepository repo){
        return new HttpCardService(repo);
    }
}
