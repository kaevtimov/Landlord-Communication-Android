package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.repositories.base.PlaceRepository;
import source.kevtimov.landlordcommunicationapp.services.base.PlaceService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

public class HttpPlaceService implements PlaceService {

    private PlaceRepository placeRepository;
    private Validator<Place> mValidator;

    public HttpPlaceService(Validator<Place> validator, PlaceRepository repository) {
        this.placeRepository = repository;
        this.mValidator = validator;
    }

    @Override
    public Place registerPlace(Place place) throws IOException {

        if(!mValidator.isObjectValid(place)){
            throw new IllegalArgumentException("Cannot add place, because it is not with valid information!");
        }

        return placeRepository.registerPlace(place);
    }

    @Override
    public List<Place> getAllPlacesWithNoTenants() throws IOException {
        return placeRepository.getAllPlacesWithNoTenants();
    }

    @Override
    public Place updatePlaceTenant(Place place, int placeId) throws IOException {
        return placeRepository.updatePlaceTenant(place, placeId);
    }

    @Override
    public List<Place> getAllPlacesByUserId(int userId) throws IOException {
        return placeRepository.getAllPlacesByUserId(userId);
    }

    @Override
    public List<Place> getAllByTenantIdAndLandlordId(int tenantId, int landlordId) throws IOException {
        return placeRepository.getAllByTenantIdAndLandlordId(tenantId, landlordId);
    }
}
