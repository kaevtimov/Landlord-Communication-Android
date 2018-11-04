package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class SqlRatingRepository implements RatingRepository{

    private HttpRequester mHttpRequester;
    private JsonParser<Rating> mJsonParser;
    private String mServerUrl;

    public SqlRatingRepository(String url, HttpRequester requester, JsonParser<Rating> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public Rating addRating(Rating rating) throws IOException {
        String requestBody = mJsonParser.toJson(rating);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<Rating> getAllByUserId(int userId) throws IOException {
        String mServerUrlGet = mServerUrl + "/" + userId;
        String json = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(json);
    }

    @Override
    public Rating checkRating(int voteForId, int voteFromId) throws IOException {
        String mServerUrlGet = mServerUrl + "/check/" + voteForId + "/" + voteFromId;
        String responseBody = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJson(responseBody);
    }
}
