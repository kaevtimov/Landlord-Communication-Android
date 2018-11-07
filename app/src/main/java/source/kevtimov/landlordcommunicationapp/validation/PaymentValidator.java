package source.kevtimov.landlordcommunicationapp.validation;

import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class PaymentValidator implements Validator<Payment> {


    @Override
    public boolean isObjectValid(Payment payment) {
        return payment != null && isUserIdValid(payment) && isRentIdValid(payment)
                && isPlaceIdValid(payment) && isCardIdValid(payment) && isAmountValid(payment)
                && isDateValid(payment);
    }

    private boolean isUserIdValid(Payment payment){
        return payment.getUserID() >= Constants.PAYMENT_USERID_MINIMUM_LENGTH;
    }

    private boolean isRentIdValid(Payment payment){
        return payment.getRentID() >= Constants.PAYMENT_RENTID_MINIMUM_LENGTH;
    }

    private boolean isCardIdValid(Payment payment){
        return payment.getCardID() >= Constants.PAYMENT_CARDID_MINIMUM_LENGTH;
    }

    private boolean isPlaceIdValid(Payment payment){
        return payment.getPlaceID() >= Constants.PAYMENT_PLACEID_MINIMUM_LENGTH;
    }

    private boolean isAmountValid(Payment payment){
        return payment.getAmount() >= Constants.PAYMENT_AMOUNT_MINIMUM_LENGTH
                && payment.getAmount() <= Constants.PAYMENT_AMOUNT_MAXIMUM_LENGTH;
    }

    private boolean isDateValid(Payment payment){
        return payment.getDate().length() >= Constants.PAYMENT_DATE_MINIMUM_LENGTH
                && payment.getDate().length() <= Constants.PAYMENT_DATE_MAXIMUM_LENGTH;
    }
}
