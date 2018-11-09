package source.kevtimov.landlordcommunicationapp.diconfig.modules;


import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.utils.bitmaprotator.BitmapRotator;
import source.kevtimov.landlordcommunicationapp.utils.bitmaprotator.IBitmapRotator;

@Module
public class BitmapRotatorModule {

    @Provides
    public IBitmapRotator getAgent(){
        return new BitmapRotator();
    }
}
