package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class SqlPaymentRepository implements PaymentRepository {

    private HttpRequester mHttpRequester;
    private JsonParser<Payment> mJsonParser;
    private String mServerUrl;

    public SqlPaymentRepository(String url, HttpRequester requester, JsonParser<Payment> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public Payment createPayment(Payment payment) throws IOException {
        String requestBody = mJsonParser.toJson(payment);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }
}
