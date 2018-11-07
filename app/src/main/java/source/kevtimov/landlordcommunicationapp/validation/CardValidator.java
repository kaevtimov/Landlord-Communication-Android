package source.kevtimov.landlordcommunicationapp.validation;

import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class CardValidator implements Validator<Card> {



    @Override
    public boolean isObjectValid(Card card) {
        return card != null && isBalanceValid(card) && isBrandNameValid(card)
                && isCardNumberValid(card) && isCardCVVValid(card) && isTypeValid(card)
                && isUserIdValid(card);
    }

    private boolean isUserIdValid(Card card){
        return card.getUserID() >= Constants.CARD_USERID_MINIMUM_LENGTH;
    }

    private boolean isBrandNameValid(Card card){
        return card.getBrand().length() >= Constants.CARD_BRAND_MINIMUM_LENGTH
                && card.getBrand().length() <= Constants.CARD_BRAND_MAXIMUM_LENGTH;
    }

    private boolean isTypeValid(Card card){
        return card.getType().length() >= Constants.CARD_TYPE_MINIMUM_LENGTH
                && card.getType().length() <= Constants.CARD_TYPE_MAXIMUM_LENGTH;
    }

    private boolean isBalanceValid(Card card){
        return card.getBalance() >= Constants.CARD_BALANCE_MINIMUM_LENGTH
                && card.getBalance() <= Constants.CARD_BALANCE_MAXIMUM_LENGTH;
    }

    private boolean isCardNumberValid(Card card){
        return card.getCardNumber().length() >= Constants.CARD_NUMBER_MINIMUM_LENGTH
                && card.getCardNumber().length() <= Constants.CARD_NUMBER_MAXIMUM_LENGTH;
    }

    private boolean isCardCVVValid(Card card){
        return card.getCvvNumber().length() >= Constants.CARD_CVV_MINIMUM_LENGTH
                && card.getCvvNumber().length() <= Constants.CARD_CVV_MAXIMUM_LENGTH;
    }
}
