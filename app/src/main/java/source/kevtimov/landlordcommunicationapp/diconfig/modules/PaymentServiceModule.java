package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.repositories.base.PaymentRepository;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpPaymentService;
import source.kevtimov.landlordcommunicationapp.services.base.PaymentService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class PaymentServiceModule {

    @Provides
    public PaymentService getService(PaymentRepository repo, Validator<Payment> validator){
        return new HttpPaymentService(repo, validator);
    }
}
