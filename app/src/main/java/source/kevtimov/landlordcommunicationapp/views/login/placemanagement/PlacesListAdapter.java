package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import source.kevtimov.landlordcommunicationapp.models.Place;

public class PlacesListAdapter extends ArrayAdapter<Place> {

    public PlacesListAdapter(@NonNull Context context, int resource) {
        super(context, -1);
    }
}

