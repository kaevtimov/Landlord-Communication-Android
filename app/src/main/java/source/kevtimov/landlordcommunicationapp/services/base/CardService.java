package source.kevtimov.landlordcommunicationapp.services.base;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Card;

public interface CardService {

    List<Card> getAllCardsByUserId(int userId) throws IOException;

    Card updateCardBalance(int cardId, Card card) throws IOException;

    Card registerCard(Card card) throws IOException;
}
