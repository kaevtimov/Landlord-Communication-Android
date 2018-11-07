package source.kevtimov.landlordcommunicationapp.repositories.base;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Card;

public interface CardRepository {

    List<Card> getAllCardsByUserId(int userId) throws IOException;

    Card updateCardBalance(int cardId, Card card) throws IOException;

    Card registerCard(Card card) throws IOException;
}
