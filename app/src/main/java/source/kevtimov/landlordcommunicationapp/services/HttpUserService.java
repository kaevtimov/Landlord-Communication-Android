package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.repositories.UserRepository;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;

public class HttpUserService implements UserService{

    private UserRepository userRepository;
    private IPasswordAgent passwordAgent;

    public HttpUserService(UserRepository repository, IPasswordAgent passwordAgent){
        this.userRepository = repository;
        this.passwordAgent = passwordAgent;
    }


    @Override
    public List<User> getLandlords() throws IOException {
        return userRepository.getLandlords();
    }

    @Override
    public User getUserByUsername(String username) throws IOException {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User checkUserLogin(String username, String password) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        User incoming = userRepository.getUserHashAndSaltByUsername(username);

        if(incoming == null){
            return null;
        }
        // check password
        String checkingPasswordHash = passwordAgent.generatePasswordHash(password, incoming.getPasswordSalt());

        if(checkingPasswordHash.equals(incoming.getPasswordHash())){
            return incoming;
        }else{
            return null;
        }
    }

}
