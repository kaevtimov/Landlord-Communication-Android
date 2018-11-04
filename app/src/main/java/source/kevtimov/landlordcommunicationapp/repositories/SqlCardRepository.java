package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.http.HttpRequester;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class SqlCardRepository implements CardRepository {

    private HttpRequester mHttpRequester;
    private JsonParser<Card> mJsonParser;
    private String mServerUrl;

    public SqlCardRepository(String url, HttpRequester requester, JsonParser<Card> jsonParser) {
        this.mServerUrl = url;
        this.mHttpRequester = requester;
        this.mJsonParser = jsonParser;
    }

    @Override
    public List<Card> getAllCardsByUserId(int userId) throws IOException {
        String mServerUrlGet = mServerUrl + "/" + userId;
        String cardJson = mHttpRequester.get(mServerUrlGet);
        return mJsonParser.fromJsonArray(cardJson);
    }

    @Override
    public Card updateCardBalance(int cardId, Card card) throws IOException {
        String requestBody = mJsonParser.toJson(card);
        String mServerUrlPut = mServerUrl + "/update/balance/" + cardId;
        String responseBody = mHttpRequester.update(mServerUrlPut, requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public Card registerCard(Card card) throws IOException {
        String requestBody = mJsonParser.toJson(card);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);
        return mJsonParser.fromJson(responseBody);
    }
}
