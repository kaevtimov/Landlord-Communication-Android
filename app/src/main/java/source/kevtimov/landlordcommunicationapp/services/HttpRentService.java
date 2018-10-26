package source.kevtimov.landlordcommunicationapp.services;

import source.kevtimov.landlordcommunicationapp.repositories.RentRepository;

public class HttpRentService implements RentService {

    private RentRepository rentRepository;

    public HttpRentService(RentRepository repository) {
        this.rentRepository = repository;
    }
}
