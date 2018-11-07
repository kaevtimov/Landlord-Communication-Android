package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.repositories.base.UserRepository;
import source.kevtimov.landlordcommunicationapp.services.base.UserService;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

public class HttpUserService implements UserService {

    private UserRepository userRepository;
    private IPasswordAgent passwordAgent;
    private Validator<User> mValidator;

    public HttpUserService(Validator<User> validator, UserRepository repository, IPasswordAgent passwordAgent) {
        this.userRepository = repository;
        this.passwordAgent = passwordAgent;
        this.mValidator = validator;
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

        if (userType.equals("custom")) {
            String passSalt = passwordAgent.getSalt(10);
            String passHash = passwordAgent.generatePasswordHash(password, passSalt);

            user.setPasswordSalt(passSalt);
            user.setPasswordHash(passHash);
        }

        if(!mValidator.isObjectValid(user)){
            throw new IllegalArgumentException("Cannot register user, because he is not with valid information!");
        }
        return userRepository.registerUser(user);
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

    @Override
    public List<User> getAllTenants() throws IOException {
        return userRepository.getAllTenants();
    }

    @Override
    public User getUserById(int userId) throws IOException {
        return userRepository.getUserById(userId);
    }
}
