package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.validation.UserValidator;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class UserValidatorModule {

    @Provides
    @Singleton
    public Validator<User> userValidator(){
        return new UserValidator();
    }
}
