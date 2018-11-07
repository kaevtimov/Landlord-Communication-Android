package source.kevtimov.landlordcommunicationapp.validation;

import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class RentValidator implements Validator<Rent> {



    @Override
    public boolean isObjectValid(Rent rent) {
        return rent != null && isPlaceIdValid(rent) && isTotalAmountValid(rent)
                && isRemAmountValid(rent) && isDueDateValid(rent);
    }

    private boolean isPlaceIdValid(Rent rent){
        return rent.getPlaceID() >= Constants.RENT_PLACEID_MINIMUM_LENGTH;
    }

    private boolean isTotalAmountValid(Rent rent){
        return rent.getTotalAmount() >= Constants.RENT_MINIMUM_TOTAL_AMOUNT
                && rent.getTotalAmount() <= Constants.RENT_MAXIMUM_TOTAL_AMOUNT;
    }

    private boolean isRemAmountValid(Rent rent){
        return rent.getRemainingAmount() >= Constants.RENT_MINIMUM_REM_AMOUNT
                && rent.getRemainingAmount() <= Constants.RENT_MAXIMUM_REM_AMOUNT;
    }

    private boolean isDueDateValid(Rent rent){
        return rent.getDueDate().length() >= Constants.RENT_DUE_DATE_MINIMUM_LENGTH
                && rent.getDueDate().length() <= Constants.RENT_DUE_DATE_MAXIMUM_LENGTH;
    }
}
