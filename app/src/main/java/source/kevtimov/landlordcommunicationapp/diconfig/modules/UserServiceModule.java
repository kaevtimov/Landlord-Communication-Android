package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.repositories.base.UserRepository;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpUserService;
import source.kevtimov.landlordcommunicationapp.services.base.UserService;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class UserServiceModule {

    @Provides
    public UserService getService(UserRepository repo, IPasswordAgent agent, Validator<User> validator){
        return new HttpUserService(validator, repo, agent);
    }
}
