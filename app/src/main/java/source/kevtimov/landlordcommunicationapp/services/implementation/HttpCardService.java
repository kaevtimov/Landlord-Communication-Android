package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.repositories.base.CardRepository;
import source.kevtimov.landlordcommunicationapp.services.base.CardService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

public class HttpCardService implements CardService {

    private CardRepository cardRepository;
    private Validator<Card> mValidator;

    public HttpCardService(Validator<Card> validator, CardRepository repository) {
        this.cardRepository = repository;
        this.mValidator = validator;
    }

    @Override
    public List<Card> getAllCardsByUserId(int userId) throws IOException {
        return cardRepository.getAllCardsByUserId(userId);
    }

    @Override
    public Card updateCardBalance(int cardId, Card card) throws IOException {
        return cardRepository.updateCardBalance(cardId, card);
    }

    @Override
    public Card registerCard(Card card) throws IOException {

        if(!mValidator.isObjectValid(card)){
            throw new IllegalArgumentException("Cannot register card, because it is not with valid information!");
        }

        return cardRepository.registerCard(card);
    }
}
