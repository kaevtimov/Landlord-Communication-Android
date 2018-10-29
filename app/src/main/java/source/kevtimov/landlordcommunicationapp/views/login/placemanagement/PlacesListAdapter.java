package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;

public class PlacesListAdapter extends ArrayAdapter<Place> {

    @BindView(R.id.iv_house)
    ImageView mImageViewPlaces;

    @BindView(R.id.tv_place_addresses)
    TextView mTextViewPlacesAddress;

    public PlacesListAdapter(@NonNull Context context, int resource) {
        super(context, -1);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_my_places_adapter, parent, false);
        }
        ButterKnife.bind(this, view);


        Place place = getItem(position);
        mTextViewPlacesAddress.setText("Address: " + Objects.requireNonNull(place).getAddress());

        return view;
    }
}

