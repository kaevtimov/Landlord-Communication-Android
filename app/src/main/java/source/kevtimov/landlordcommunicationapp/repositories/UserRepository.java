package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface UserRepository {

    User getUserByUsername(String username) throws IOException;

    User getUserHashAndSaltByUsername(String username) throws IOException;
}
