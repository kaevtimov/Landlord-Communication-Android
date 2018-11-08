package source.kevtimov.landlordcommunicationapp.views.login.userdetails;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rating;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.bitmapcache.BitmapCache;
import source.kevtimov.landlordcommunicationapp.views.login.myusers.ContractsMyUsers;

public class UserDetailsFragment extends Fragment implements ContractsUserDetails.View, BaseRatingBar.OnRatingChangeListener {

    @BindView(R.id.iv_user_pic)
    ImageView mImageView;

    @BindView(R.id.iv_star)
    ImageView mImageViewStar;

    @BindView(R.id.user_name)
    TextView mUserName;

    @BindView(R.id.rating)
    TextView mUserRating;

    @BindView(R.id.user_username)
    TextView mUserUserName;

    @BindView(R.id.user_email)
    TextView mUserEmail;

    @BindView(R.id.place_info)
    TextView mPlaceInfo;

    @BindView(R.id.rating_bar)
    ScaleRatingBar mRatingBar;

    @BindView(R.id.progress_bar)
    CircleProgressBar mProgressBar;

    private ContractsUserDetails.Presenter mPresenter;
    private BitmapCache mBitmapCache;

    @Inject
    public UserDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, root);
        mBitmapCache = BitmapCache.getInstance();

        mRatingBar.setOnRatingChangeListener(this);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUser();
        mPresenter.getRating();
        mPresenter.loadPlaces();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }


    @Override
    public void setPresenter(ContractsUserDetails.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
    }

    @Override
    public void showRating(double rating) {
        mUserRating.setText(String.valueOf(rating));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUser(User user) {
        if(mBitmapCache.getLruCache().get(user.getUsername() + "_profile_image") != null){
            mImageView.setImageBitmap((Bitmap) mBitmapCache.getLruCache().get(user.getUsername() + "_profile_image"));
        }
        mUserEmail.setText("EMAIL: " + user.getEmail());
        mUserUserName.setText("USERNAME: " + user.getUsername());
        mUserName.setText("FULL NAME: " + user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public void alertAlreadyVoted() {
        StyleableToast.makeText(getContext(), "ALREADY VOTED!",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void showInfo(Rating rating) {
        StyleableToast.makeText(getContext(), "VOTE ADDED! YOUR VOTE IS " + rating.getRating() + "!",
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
    }

    @Override
    public void showPlaces(List<Place> places) {
        if(places.size() > 0){
            mPlaceInfo.setText("");
            for (Place pl:places) {
                mPlaceInfo.append("Place information:\n");
                mPlaceInfo.append(pl.toString());
            }
        }
    }

    @Override
    public void onRatingChange(BaseRatingBar baseRatingBar, float rating) {
        mPresenter.checkRating(rating);
    }
}
