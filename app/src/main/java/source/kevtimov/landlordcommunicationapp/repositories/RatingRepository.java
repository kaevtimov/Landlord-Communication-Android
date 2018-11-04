package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Rating;

public interface RatingRepository {

    Rating addRating(Rating rating) throws IOException;

    List<Rating> getAllByUserId(int userId) throws IOException;

    Rating checkRating(int voteForId, int voteFromId) throws IOException;
}
