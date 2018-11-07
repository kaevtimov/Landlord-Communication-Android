package source.kevtimov.landlordcommunicationapp.validation;

import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class UserValidator implements Validator<User> {


    @Override
    public boolean isObjectValid(User user) {
        return user != null && isUsernameValid(user) && isUserFirstNameValid(user)
                && isUserLastNameValid(user) && isUserEmailValid(user) && isPasswordHashValid(user)
                && isPasswordSaltValid(user);
    }

    private boolean isUsernameValid(User user){
        return user.getUsername().length() >= Constants.USER_USERNAME_MINIMUM_LENGTH
                && user.getUsername().length() <= Constants.USER_USERNAME_MAXIMUM_LENGTH;
    }

    private boolean isUserFirstNameValid(User user){
        return user.getFirstName().length() >= Constants.USER_FIRST_NAME_MINIMUM_LENGTH
                && user.getFirstName().length() <= Constants.USER_FIRST_NAME_MAXIMUM_LENGTH;
    }

    private boolean isUserLastNameValid(User user){
        return user.getLastName().length() >= Constants.USER_LAST_NAME_MINIMUM_LENGTH
                && user.getLastName().length() <= Constants.USER_LAST_NAME_MAXIMUM_LENGTH;
    }

    private boolean isUserEmailValid(User user){
        return user.getEmail().length() >= Constants.MINIMUM_EMAIL_LENGTH
                && user.getEmail().length() <= Constants.MAXIMUM_EMAIL_LENGTH;
    }

    private boolean isPasswordHashValid(User user){
        return user.getPasswordHash().length() <= Constants.USER_MAXIMUM_PASS_HASH;
    }

    private boolean isPasswordSaltValid(User user){
        return user.getPasswordSalt().length() <= Constants.USER_MAXIMUM_PASS_SALT;
    }
}
