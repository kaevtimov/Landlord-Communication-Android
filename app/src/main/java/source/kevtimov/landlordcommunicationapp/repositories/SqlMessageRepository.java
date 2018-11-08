package source.kevtimov.landlordcommunicationapp.repositories;

import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;
import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Message;

public class SqlMessageRepository implements MessageRepository {
    private HttpRequester mHttpRequester;
    private JsonParser<Message> mJsonParser;
    private String mServerUrl;

    public SqlMessageRepository(HttpRequester mHttpRequester, JsonParser<Message> mJsonParser, String mServerUrl) {
        this.mHttpRequester = mHttpRequester;
        this.mJsonParser = mJsonParser;
        this.mServerUrl = mServerUrl;
    }

    @Override
    public Message sendMessage(Message message) throws IOException {
        String postUrl = mServerUrl + "/newmessage";
        String requestBody = mJsonParser.toJson(message);
        String responseBody = mHttpRequester.post(postUrl,requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public Message receiveMessage() throws IOException {
        String mServerUrlGet = "https://fcm.googleapis.com/v1/projects/landlordproject-7f76e/messages:send";
        String messageJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJson(messageJson);
    }
}
