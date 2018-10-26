package source.kevtimov.landlordcommunicationapp.repositories;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class SqlRentRepository implements RentRepository {

    private HttpRequester mHttpRequester;
    private JsonParser<Rent> mJsonParser;
    private String mServerUrl;

    public SqlRentRepository(String url, HttpRequester requester, JsonParser<Rent> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }
}
