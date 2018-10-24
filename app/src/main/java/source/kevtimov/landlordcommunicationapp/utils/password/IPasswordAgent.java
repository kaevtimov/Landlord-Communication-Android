package source.kevtimov.landlordcommunicationapp.utils.password;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IPasswordAgent {

    String generatePasswordHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
