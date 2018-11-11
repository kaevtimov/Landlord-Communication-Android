package source.kevtimov.landlordcommunicationapp.repositories.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import source.kevtimov.landlordcommunicationapp.repositories.base.MessageRepository;

public class SqlMessageRepository implements MessageRepository {

    private HttpRequester mHttpRequester;
    private JsonParser<Message> mJsonParser;
    private String mServerUrl;

    public SqlMessageRepository(String url, HttpRequester requester, JsonParser<Message> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }


    @Override
    public Message createMessage(Message message) throws IOException {
        String requestBody = mJsonParser.toJson(message);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public List<Message> getMessagesByChatId(int chatId) throws IOException {
        String mServerUrlGet = mServerUrl + "/chat/" + chatId;
        String userJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(userJson);
    }
}
