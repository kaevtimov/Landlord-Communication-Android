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

    @Override
    public Rent getRentByPlaceId(int placeId) throws IOException {
        return rentRepository.getRentByPlaceId(placeId);
    }

    @Override
    public Rent updatePaidStatus(int rentId) throws IOException {
        return rentRepository.updatePaidStatus(rentId);
    }

    @Override
    public Rent updateRentRemainingAmount(int rentId, Rent rent) throws IOException {
        return rentRepository.updateRentRemainingAmount(rentId, rent);
    }
}
