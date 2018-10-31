package source.kevtimov.landlordcommunicationapp.views.login.placedetails;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.vstechlab.easyfonts.EasyFonts;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;

public class PlaceDetailsFragment extends Fragment implements ContractsPlaceDetails.View{

    @BindView(R.id.tv_address)
    TextView mAddress;

    @BindView(R.id.tv_description)
    TextView mDescription;

    @BindView(R.id.tv_landlord_info)
    TextView mLandlordInfo;

    @BindView(R.id.tv_tenant_info)
    TextView mTenantInfo;

    @BindView(R.id.tv_rents)
    TextView mRentInfo;

    @BindView(R.id.btn_pay)
    Button mPayButton;

    @BindView(R.id.progress_bar)
    CircleProgressBar mProgressBar;

    private ContractsPlaceDetails.Navigator mNavigator;
    private ContractsPlaceDetails.Presenter mPresenter;


    @Inject
    public PlaceDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_place_details, container, false);
        ButterKnife.bind(this, root);

        initFonts();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.getNotLogInUser();
        mPresenter.getUnpaidRent();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsPlaceDetails.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsPlaceDetails.Navigator navigator) {
        this.mNavigator = navigator;
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
    public void showEmptyDetails() {
        StyleableToast.makeText(getContext(), "Empty!",
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
    }

    @Override
    public void navigateToPayRent(Place mPlace) {
        mNavigator.navigateToPayRent(mPlace);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void manageViewsWithTenant(User notLoggedInUser, User loggedInUser, Place mPlace) {
        if(loggedInUser.isLandlord()){
            mLandlordInfo.setText("Landlord: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "\n"
                    + "Username: " + loggedInUser.getUsername());
            mTenantInfo.setText("Tenant: " + notLoggedInUser.getFirstName() + " " + notLoggedInUser.getLastName() + "\n"
                    + "Username: " + notLoggedInUser.getUsername());
            mPayButton.setVisibility(View.GONE);

            mPresenter.getUnpaidRent();
        }else{
            mLandlordInfo.setText("Landlord: " + notLoggedInUser.getFirstName() + " " + notLoggedInUser.getLastName() + "\n"
                    + "Username: " + notLoggedInUser.getUsername());
            mTenantInfo.setText("Tenant: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "\n"
                    + "Username: " + loggedInUser.getUsername());
            mPresenter.getUnpaidRent();
        }
        mAddress.setText("Address: " + mPlace.getAddress());
        mDescription.setText("Description: " + mPlace.getDescription());
    }

    @OnClick(R.id.btn_pay)
    public void onPayClick(View v){
        mPresenter.allowNavigationToPayRent();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void manageViewsWithNoTenant(User mLogInUser, Place mPlace) {
        mLandlordInfo.setText("Landlord: " + mLogInUser.getFirstName() + " " + mLogInUser.getLastName() + "\n"
                + "Username: " + mLogInUser.getUsername());
        mPayButton.setVisibility(View.GONE);

        mRentInfo.setText("Rents information: NO RENT INFORMATION\n");

        mAddress.setText("Address: " + mPlace.getAddress());
        mDescription.setText("Description: " + mPlace.getDescription());
    }

    @Override
    public void viewRent(Rent rent) {
        mRentInfo.setText("Due date: " + rent.getDueDate() + "\n" + "Total amount: " + rent.getTotalAmount() + " leva\n"
                + "Rem. amount: " + rent.getRemainingAmount() + " leva\n" + "Paid: " + rent.isPaid());
    }

    @Override
    public void viewEmptyRent() {
        mRentInfo.setText("Rents information: NO RENT INFORMATION\n");
        mPayButton.setVisibility(View.GONE);
    }

    private void initFonts() {
        mAddress.setTypeface(EasyFonts.droidSerifBold(getContext()));
        mDescription.setTypeface(EasyFonts.droidSerifBold(getContext()));
        mLandlordInfo.setTypeface(EasyFonts.droidSerifBold(getContext()));
        mTenantInfo.setTypeface(EasyFonts.droidSerifBold(getContext()));
        mRentInfo.setTypeface(EasyFonts.droidSerifBold(getContext()));
    }
}
