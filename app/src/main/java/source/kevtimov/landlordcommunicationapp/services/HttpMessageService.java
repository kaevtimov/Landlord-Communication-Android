package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class HttpMessageService implements MessageService {
    private HttpRequester mHttpRequester;
    private JsonParser<Message> mMessageParser;
    private String mServerUrl;

    public HttpMessageService(HttpRequester mHttpRequester, JsonParser<Message> mMessageParser, String mServerUrl) {
        this.mHttpRequester = mHttpRequester;
        this.mMessageParser = mMessageParser;
        this.mServerUrl = mServerUrl;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        String url = mServerUrl + "/messages";
        String json = mMessageParser.toJson(message);
        mHttpRequester.post(url,json);
    }
}
