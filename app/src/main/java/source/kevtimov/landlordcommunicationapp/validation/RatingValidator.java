package source.kevtimov.landlordcommunicationapp.validation;

import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class RatingValidator implements Validator<Rating> {



    @Override
    public boolean isObjectValid(Rating rating) {
        return rating != null && isRatingValid(rating) && isVoteForIdValid(rating)
                && isVoteFromIdValid(rating);
    }

    private boolean isVoteForIdValid(Rating rating){
        return rating.getVoteFor() >= Constants.RATING_VOTEFORID_MINIMUM_LENGTH;
    }

    private boolean isVoteFromIdValid(Rating rating){
        return rating.getVoteFrom() >= Constants.RATING_VOTEFROMID_MINIMUM_LENGTH;
    }

    private boolean isRatingValid(Rating rating){
        return rating.getRating() >= Constants.RATING_AMOUNT_MINIMUM_LENGTH
                && rating.getRating() <= Constants.RATING_AMOUNT_MAXIMUM_LENGTH;
    }

}
