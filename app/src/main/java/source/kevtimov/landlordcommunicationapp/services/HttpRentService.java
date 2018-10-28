package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.repositories.RentRepository;

public class HttpRentService implements RentService {

    private RentRepository rentRepository;

    public HttpRentService(RentRepository repository) {
        this.rentRepository = repository;
    }

    @Override
    public Rent registerRent(Rent rent) throws IOException {
        return rentRepository.registerRent(rent);
    }
}
