package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Rent;

public interface RentRepository {

    Rent registerRent(Rent rent) throws IOException;
}
