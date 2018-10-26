package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.repositories.UserRepository;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;

public class HttpUserService implements UserService {

    private UserRepository userRepository;
    private IPasswordAgent passwordAgent;

    public HttpUserService(UserRepository repository, IPasswordAgent passwordAgent) {
        this.userRepository = repository;
        this.passwordAgent = passwordAgent;
    }


    @Override
    public User getUserByUsername(String username) throws IOException {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User checkUserLogin(String username, String password) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        User incoming = userRepository.getUserHashAndSaltByUsername(username);

        if (incoming == null) {
            return null;
        }
        // check password
        String checkingPasswordHash = passwordAgent.generatePasswordHash(password, incoming.getPasswordSalt());

        if (checkingPasswordHash.equals(incoming.getPasswordHash())) {
            return incoming;
        } else {
            return null;
        }
    }

    @Override
    public User registerUser(User user, String password, String userType) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        User userToRegister = user;

        if (userType.equals("custom")) {
            String passSalt = passwordAgent.getSalt(10);
            String passHash = passwordAgent.generatePasswordHash(password, passSalt);

            userToRegister.setPasswordSalt(passSalt);
            userToRegister.setPasswordHash(passHash);
        }

        return userRepository.registerUser(userToRegister);
    }

    @Override
    public User checkUsernameAndEmail(String username, String email) throws IOException {
        User usernameModel = userRepository.checkUsernameForExisting(username);
        User emailModel = userRepository.checkEmailForExisting(email);
        User returnUser = new User();
        returnUser.setEmail(emailModel.getEmail());
        returnUser.setUsername(usernameModel.getUsername());

        return returnUser;
    }
}
