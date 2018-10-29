package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.UserRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpUserService;
import source.kevtimov.landlordcommunicationapp.services.UserService;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;

@Module
public class UserServiceModule {

    @Provides
    public UserService getService(UserRepository repo, IPasswordAgent agent){
        return new HttpUserService(repo, agent);
    }
}
