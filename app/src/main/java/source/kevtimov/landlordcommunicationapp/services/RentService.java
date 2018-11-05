package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Rent;

public interface RentService {

    Rent registerRent(Rent rent)throws IOException;

    Rent getRentByPlaceId(int placeId) throws IOException;

    Rent updatePaidStatus(int rentId) throws IOException;

    Rent updateRentRemainingAmount(int rentId, Rent rent) throws IOException;

    Rent editRent(Rent rent, int rentId) throws IOException;
}
