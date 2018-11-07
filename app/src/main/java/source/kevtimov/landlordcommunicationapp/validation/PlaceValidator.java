package source.kevtimov.landlordcommunicationapp.validation;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class PlaceValidator implements Validator<Place> {



    @Override
    public boolean isObjectValid(Place place) {
        return place != null && isAddressValid(place) && isDescriptionValid(place)
                && isTenantIdValid(place) && isLandlordIdValid(place);
    }

    private boolean isAddressValid(Place place){
        return place.getAddress().length() >= Constants.PLACE_ADDRESS_MINIMUM_LENGTH
                && place.getAddress().length() <= Constants.PLACE_ADDRESS_MAXIMUM_LENGTH;
    }

    private boolean isDescriptionValid(Place place){
        return place.getDescription().length() >= Constants.PLACE_DESCRIPTION_MINIMUM_LENGTH
                && place.getDescription().length() <= Constants.PLACE_DESCRIPTION_MAXIMUM_LENGTH;
    }

    private boolean isTenantIdValid(Place place){
        return place.getTenantID() >= Constants.PLACE_TENANTID_MINIMUM_LENGTH;
    }

    private boolean isLandlordIdValid(Place place){
        return place.getLandlordID() >= Constants.PLACE_LANDLORDID_MINIMUM_LENGTH;
    }
}
