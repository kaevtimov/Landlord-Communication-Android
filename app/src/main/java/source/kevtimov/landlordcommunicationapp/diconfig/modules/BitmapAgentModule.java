package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.BitmapAgent;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcoder.IBitmapAgent;

@Module
public class BitmapAgentModule {

    @Provides
    public IBitmapAgent getAgent(){
        return new BitmapAgent();
    }
}
