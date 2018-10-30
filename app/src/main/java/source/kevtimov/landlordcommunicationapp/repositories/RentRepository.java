package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Rent;

public interface RentRepository {

    Rent registerRent(Rent rent) throws IOException;

    Rent getRentByPlaceId(int placeId) throws IOException;

    Rent updatePaidStatus(int rentId) throws IOException;

    Rent updateRentRemainingAmount(int rentId, Rent rent) throws IOException;
}
