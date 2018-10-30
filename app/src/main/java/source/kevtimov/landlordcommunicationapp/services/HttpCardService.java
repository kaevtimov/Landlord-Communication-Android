package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.repositories.CardRepository;

public class HttpCardService implements CardService {

    private CardRepository cardRepository;

    public HttpCardService(CardRepository repository) {
        this.cardRepository = repository;
    }

    @Override
    public List<Card> getAllCardsByUserId(int userId) throws IOException {
        return cardRepository.getAllCardsByUserId(userId);
    }

    @Override
    public Card updateCardBalance(int cardId, Card card) throws IOException {
        return cardRepository.updateCardBalance(cardId, card);
    }
}
