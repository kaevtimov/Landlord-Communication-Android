package source.kevtimov.landlordcommunicationapp.repositories;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Place;

public interface PlaceRepository {

    Place registerPlace(Place place) throws IOException;

}
