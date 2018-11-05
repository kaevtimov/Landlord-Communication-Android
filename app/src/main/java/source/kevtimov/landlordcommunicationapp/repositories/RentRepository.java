package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;
import java.io.ObjectInput;

import io.reactivex.internal.schedulers.IoScheduler;
import source.kevtimov.landlordcommunicationapp.models.Rent;

public interface RentRepository {

    Rent registerRent(Rent rent) throws IOException;

    Rent getRentByPlaceId(int placeId) throws IOException;

    Rent updatePaidStatus(int rentId) throws IOException;

    Rent updateRentRemainingAmount(int rentId, Rent rent) throws IOException;

    Rent editRent(Rent rent, int rentId) throws IOException;
}
