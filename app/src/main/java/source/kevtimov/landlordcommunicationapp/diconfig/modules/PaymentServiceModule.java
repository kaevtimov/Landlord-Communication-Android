package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.PaymentRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpPaymentService;
import source.kevtimov.landlordcommunicationapp.services.PaymentService;

@Module
public class PaymentServiceModule {

    @Provides
    public PaymentService getService(PaymentRepository repo){
        return new HttpPaymentService(repo);
    }
}
