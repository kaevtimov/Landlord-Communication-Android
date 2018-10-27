package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Rent;

public interface RentService {

    Rent registerRent(Rent rent)throws IOException;
}
