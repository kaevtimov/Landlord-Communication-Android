package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.repositories.RatingRepository;

public class HttpRatingService implements RatingService{

    private RatingRepository ratingRepository;

    public HttpRatingService(RatingRepository repository) {
        this.ratingRepository = repository;
    }

    @Override
    public Rating addRating(Rating rating) throws IOException {
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
