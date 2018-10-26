package source.kevtimov.landlordcommunicationapp.repositories;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class SqlPlaceRepository implements PlaceRepository {

    private HttpRequester mHttpRequester;
    private JsonParser<Place> mJsonParser;
    private String mServerUrl;

    public SqlPlaceRepository(String url, HttpRequester requester, JsonParser<Place> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }
}
