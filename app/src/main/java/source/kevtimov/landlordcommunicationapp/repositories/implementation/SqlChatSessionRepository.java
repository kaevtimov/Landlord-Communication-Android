package source.kevtimov.landlordcommunicationapp.repositories.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.base.ChatSessionRepository;

public class SqlChatSessionRepository implements ChatSessionRepository {


    private HttpRequester mHttpRequester;
    private JsonParser<ChatSession> mJsonParser;
    private String mServerUrl;

    public SqlChatSessionRepository(String url, HttpRequester requester, JsonParser<ChatSession> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }


    @Override
    public ChatSession createChat(ChatSession chat) throws IOException {
        String requestBody = mJsonParser.toJson(chat);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<ChatSession> checkIfChatSessionExistsByTenantIdAndLandlordId(int tenantId, int landlordId) throws IOException {
        String mServerUrlGet = mServerUrl + "/check/" + tenantId + "/" + landlordId;
        String userJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(userJson);
    }

    @Override
    public List<ChatSession> getAllSessionsByTenantId(int tenantId) throws IOException {
        String mServerUrlGet = mServerUrl + "/tenant/" + tenantId;
        String userJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(userJson);
    }

    @Override
    public List<ChatSession> getAllSessionsByLandlordId(int landlordId) throws IOException {
        String mServerUrlGet = mServerUrl + "/landlord/" + landlordId;
        String userJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(userJson);
    }
}
