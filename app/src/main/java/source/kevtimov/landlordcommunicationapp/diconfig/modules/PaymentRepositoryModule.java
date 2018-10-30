package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.PaymentRepository;
import source.kevtimov.landlordcommunicationapp.repositories.SqlPaymentRepository;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

@Module
public class PaymentRepositoryModule {

    @Provides
    @Singleton
    public PaymentRepository paymentRepository(HttpRequester requester, JsonParser<Payment> parser) {
        String url = Constants.BASE_SERVER_URL_KRIS + "/payments";
        return new SqlPaymentRepository(url, requester, parser);
    }
}
