package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.repositories.base.RentRepository;
import source.kevtimov.landlordcommunicationapp.services.base.RentService;
import source.kevtimov.landlordcommunicationapp.validation.Validator;

public class HttpRentService implements RentService {

    private RentRepository rentRepository;
    private Validator<Rent> mValidator;

    public HttpRentService(Validator<Rent> validator, RentRepository repository) {
        this.rentRepository = repository;
        this.mValidator = validator;
    }

    @Override
    public Rent registerNextRent(Rent rent) throws IOException {

        if(!mValidator.isObjectValid(rent)){
            throw new IllegalArgumentException("Cannot add rent, because it is not with required information!");
        }

        return rentRepository.registerNextRent(rent);
    }

    @Override
    public Rent registerFirstRent(Rent rent) throws IOException {

        if(!mValidator.isObjectValid(rent)){
            throw new IllegalArgumentException("Cannot add rent, because it is not with required information!");
        }

        return rentRepository.registerFirstRent(rent);
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

    @Override
    public Rent editRent(Rent rent, int rentId) throws IOException {
        return rentRepository.editRent(rent,rentId);
    }
}
