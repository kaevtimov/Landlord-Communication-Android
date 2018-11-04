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
        String currentUrl = mServerUrl + "/username/" + username;
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
    public User registerUser(User userToRegister) throws IOException {
        String requestBody = mJsonParser.toJson(userToRegister);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public User checkUsernameForExisting(String username) throws IOException {
        String currentUrl = mServerUrl + "/checkusername/" + username;
        String json = mHttpRequester.get(currentUrl);
        return mJsonParser.fromJson(json);
    }

    @Override
    public User checkEmailForExisting(String email) throws IOException {
        String currentUrl = mServerUrl + "/checkemail/" + email;
        String json = mHttpRequester.get(currentUrl);
        return mJsonParser.fromJson(json);
    }

    @Override
    public List<User> getAllTenants() throws IOException {
        String mServerUrlGet = mServerUrl + "/tenants";
        String userJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(userJson);
    }

    @Override
    public User getUserById(int userId) throws IOException {
        String url = mServerUrl + "/" + userId;
        String responseBody = mHttpRequester.get(url);
        return mJsonParser.fromJson(responseBody);
    }
}
