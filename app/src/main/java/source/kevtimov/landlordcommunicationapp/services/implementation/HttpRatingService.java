package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.repositories.base.RatingRepository;
import source.kevtimov.landlordcommunicationapp.services.base.RatingService;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

public class HttpRatingService implements RatingService {

    private RatingRepository ratingRepository;
    private Validator<Rating> mValidator;

    public HttpRatingService(RatingRepository repository, Validator<Rating> validator) {
        this.ratingRepository = repository;
        this.mValidator = validator;
    }

    @Override
    public Rating addRating(Rating rating) throws IOException {

        if(!mValidator.isObjectValid(rating)){
            throw new IllegalArgumentException(Constants.RATING_VALIDATOR_SERVICE);
        }

        return ratingRepository.addRating(rating);
    }

    @Override
    public List<Rating> getAllByUserId(int userId) throws IOException {
        return ratingRepository.getAllByUserId(userId);
    }

    @Override
    public Rating checkRating(int voteForId, int voteFromId) throws IOException {
        return ratingRepository.checkRating(voteForId, voteFromId);
    }
}
