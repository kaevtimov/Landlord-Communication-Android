package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.repositories.PlaceRepository;

public class HttpPlaceService implements PlaceService {

    private PlaceRepository placeRepository;

    public HttpPlaceService(PlaceRepository repository) {
        this.placeRepository = repository;
    }

    @Override
    public Place registerPlace(Place place) throws IOException {

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
}
