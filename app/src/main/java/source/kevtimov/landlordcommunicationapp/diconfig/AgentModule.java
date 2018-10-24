package source.kevtimov.landlordcommunicationapp.diconfig;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.utils.password.IPasswordAgent;
import source.kevtimov.landlordcommunicationapp.utils.password.PasswordAgent;

@Module
public class AgentModule {
    @Provides
    public IPasswordAgent passwordAgent() {
        return new PasswordAgent();
    }
}
