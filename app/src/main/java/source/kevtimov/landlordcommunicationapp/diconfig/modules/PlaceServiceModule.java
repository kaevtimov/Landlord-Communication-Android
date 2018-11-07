package source.kevtimov.landlordcommunicationapp.diconfig.modules;

import dagger.Module;
import dagger.Provides;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.repositories.base.PlaceRepository;
import source.kevtimov.landlordcommunicationapp.services.implementation.HttpPlaceService;
import source.kevtimov.landlordcommunicationapp.services.base.PlaceService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

@Module
public class PlaceServiceModule {

    @Provides
    public PlaceService getService(PlaceRepository repo, Validator<Place> validator){
        return new HttpPlaceService(validator, repo);
    }
}
