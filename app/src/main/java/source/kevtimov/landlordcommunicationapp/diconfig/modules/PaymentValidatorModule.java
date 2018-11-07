package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.validation.PaymentValidator;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class PaymentValidatorModule {

    @Provides
    @Singleton
    public Validator<Payment> paymentValidator(){
        return new PaymentValidator();
    }
}