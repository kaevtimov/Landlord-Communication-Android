package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface UserService {

    User getUserByUsername(String username) throws IOException;

    User checkUserLogin(String username, String password) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException;
}
