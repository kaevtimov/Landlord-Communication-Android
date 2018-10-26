package source.kevtimov.landlordcommunicationapp.services;

import source.kevtimov.landlordcommunicationapp.repositories.PlaceRepository;

public class HttpPlaceService implements PlaceService {

    private PlaceRepository placeRepository;

    public HttpPlaceService(PlaceRepository repository) {
        this.placeRepository = repository;
    }


}
