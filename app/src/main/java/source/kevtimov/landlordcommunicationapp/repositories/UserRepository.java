package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface UserRepository {

    User getUserByUsername(String username) throws IOException;

    User getUserHashAndSaltByUsername(String username) throws IOException;

    User registerUser(User userToRegister) throws IOException;

    User checkUsernameForExisting(String username) throws IOException;

    User checkEmailForExisting(String email) throws IOException;

    List<User> getAllTenants() throws  IOException;

    User getUserById(int userId) throws IOException;
}
