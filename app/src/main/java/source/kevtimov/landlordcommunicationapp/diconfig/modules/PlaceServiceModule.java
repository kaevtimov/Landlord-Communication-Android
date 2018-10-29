package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.repositories.PlaceRepository;
import source.kevtimov.landlordcommunicationapp.services.HttpPlaceService;
import source.kevtimov.landlordcommunicationapp.services.PlaceService;

@Module
public class PlaceServiceModule {

    @Provides
    public PlaceService getService(PlaceRepository repo){
        return new HttpPlaceService(repo);
    }
}
