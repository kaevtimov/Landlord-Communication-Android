package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.repositories.base.CardRepository;
import source.kevtimov.landlordcommunicationapp.services.base.CardService;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpCardService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class CardServiceModule {

    @Provides
    public CardService getService(CardRepository repo, Validator<Card> validator){
        return new HttpCardService(validator, repo);
    }
}
