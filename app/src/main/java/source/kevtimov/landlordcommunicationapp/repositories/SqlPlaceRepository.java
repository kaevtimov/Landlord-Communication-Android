package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

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

    @Override
    public List<Place> getAllPlacesWithNoTenants() throws IOException {
        String mServerUrlGet = mServerUrl + "/notenant";
        String moviesJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(moviesJson);
    }

    @Override
    public Place updatePlaceTenant(Place place, int placeId) throws IOException {
        String requestBody = mJsonParser.toJson(place);
        String mServerUrlPut = mServerUrl + "/" + placeId;
        String responseBody = mHttpRequester.update(mServerUrlPut, requestBody);
        return mJsonParser.fromJson(responseBody);
    }
}