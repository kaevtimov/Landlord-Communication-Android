package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import source.kevtimov.landlordcommunicationapp.R;
//import source.kevtimov.landlordcommunicationapp.models.Place;
//
//public class PlacesListAdapter extends ArrayAdapter<Place> {
//
//
//    @BindView(R.id.tv_name)
//    TextView mNameTextView;
//
//    @BindView(R.id.tv_year)
//    TextView mYearTextView;
//
//    @BindView(R.id.image)
//    ImageView mImageViewCamera;
//
//    @BindView(R.id.tv_rating)
//    TextView mRatingTextView;
//
//    @BindView(R.id.iv_star)
//    ImageView mImageViewStar;
//
//    @Inject
//    public PlacesListAdapter(@NonNull Context context) {
//        super(context, -1);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
//        View view = convertView;
//        if (view == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(R.layout.places_items, parent, false);
//        }
//        ButterKnife.bind(this, view);
//
//
//        Movie movie = getItem(position);
//        mNameTextView.setText(movie.getName());
//        mNameTextView.setTextColor(Color.WHITE);
//        String movieYear = "" + movie.getYear();
//        mYearTextView.setText(movieYear);
//        mYearTextView.setTextColor(Color.WHITE);
//        String rating = String.valueOf(movie.getRating());
//        rating = rating.substring(0, 3);
//        mRatingTextView.setText(rating);
//        mRatingTextView.setTextColor(Color.BLACK);
//
//        return view;
//
//    }
//}

