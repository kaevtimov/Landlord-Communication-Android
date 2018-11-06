package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class SqlUserRepository implements UserRepository {
    private HttpRequester mHttpRequester;
    private JsonParser<User> mJsonParser;
    private String mServerUrl;

    public SqlUserRepository(String url, HttpRequester requester, JsonParser<User> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public User getUserByUsername(String username) throws IOException {
        String currentUrl = mServerUrl + "/" + username;
        String json = mHttpRequester.get(currentUrl);
        return mJsonParser.fromJson(json);
    }

    @Override
    public User getUserHashAndSaltByUsername(String username) throws IOException {
        String currentUrl = mServerUrl + "/gethashandsalt/" + username;
        String json = mHttpRequester.get(currentUrl);
        return mJsonParser.fromJson(json);
    }

    @Override
    public List<User> getLandlords() throws IOException {
        String landlords = mHttpRequester.get(mServerUrl);
        return mJsonParser.fromJsonArray(landlords);
    }
}
