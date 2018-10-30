package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.parsers.GsonJsonParser;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

@Module
public class PaymentParserModule {

    @Provides
    public JsonParser<Payment> paymentJsonParser() {
        return new GsonJsonParser<Payment>(Payment.class,Payment[].class);
    }
}
