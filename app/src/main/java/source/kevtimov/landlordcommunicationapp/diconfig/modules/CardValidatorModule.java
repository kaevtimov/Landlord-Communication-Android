package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.validation.CardValidator;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class CardValidatorModule {

    @Provides
    @Singleton
    public Validator<Card> cardValidator(){
        return new CardValidator();
    }
}
