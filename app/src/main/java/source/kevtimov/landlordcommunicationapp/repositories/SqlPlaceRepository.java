package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;

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

    @Override
    public Place registerPlace(Place place) throws IOException {
        String requestBody = mJsonParser.toJson(place);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }
}
