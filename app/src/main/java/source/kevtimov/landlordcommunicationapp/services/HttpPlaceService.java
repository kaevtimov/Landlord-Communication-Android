package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

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
}
