package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;

public class PlacesListAdapter extends ArrayAdapter<Place> {

    @BindView(R.id.iv_accept)
    ImageView mImageViewPlaces;

    @BindView(R.id.tv_place_addresses)
    TextView mTextViewPlacesAddress;

    public PlacesListAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_place_adapter, parent, false);
        }
        ButterKnife.bind(this, view);


        Place place = getItem(position);
        mTextViewPlacesAddress.setText("Address: " + Objects.requireNonNull(place).getAddress());
        initFont();

        return view;
    }

    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString("font_list", "1"));

        switch (selectedFont) {
            case 1:
                mTextViewPlacesAddress.setTypeface(EasyFonts.droidSerifBold(getContext()));
                break;
            case 2:
                mTextViewPlacesAddress.setTypeface(EasyFonts.funRaiser(getContext()));
                break;
            case 3:
                mTextViewPlacesAddress.setTypeface(EasyFonts.walkwayBold(getContext()));
                break;

        }
    }
}

