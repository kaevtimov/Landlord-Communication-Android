package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Place;

public interface PlaceService {


    Place registerPlace(Place place) throws IOException;

    List<Place> getAllPlacesWithNoTenants() throws IOException;

    Place updatePlaceTenant(Place place, int placeId) throws IOException;
}
