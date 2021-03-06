package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class PlaceManagementFragment extends Fragment implements ContractsPlaceManagement.View {

    private ContractsPlaceManagement.Presenter mPresenter;
    private ContractsPlaceManagement.Navigator mNavigator;
    private PlacesListAdapter mPlaceAdapter;
    private User mUser;  // user incoming from previous activity
    private Bundle mIncomingPlaceAndRentInfo;

    @BindView(R.id.tv_enter_places)
    TextView mTextViewEnterPlaces;

    @BindView(R.id.lv_places)
    ListView mListViewSelectedPlaces;

    @BindView(R.id.btn_add_place)
    Button mButtonAddPlace;

    @BindView(R.id.btn_select_place)
    Button mButtonSelectPlace;

    @BindView(R.id.progress_bar_manage)
    CircleProgressBar mLoadingView;


    @Inject
    public PlaceManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_place_management, container, false);

        ButterKnife.bind(this, root);

        manageView();
        initFont();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }


    @Override
    public void setPresenter(ContractsPlaceManagement.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsPlaceManagement.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showLoading() {
        this.mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void navigateUserToAddPlace() {
        mNavigator.navigateToAddPlaceActivity();
    }

    @Override
    public void navigateUserToSelectPlace() {
        mNavigator.navigateToSelectPlaceActivity();
    }

    @Override
    public void addPlaceFail() {
        StyleableToast.makeText(getContext(), Constants.ADD_PLACE_FAILED,
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @OnClick(R.id.btn_add_place)
    public void onClickAdd(View v) {
        mPresenter.allowNavigationToAddPlace();
    }

    @OnClick(R.id.btn_select_place)
    public void onClickSelect(View v) {
        mPresenter.allowNavigationToSelectPlace();
    }


    @Override
    public void updateAddPlaces(Bundle incomingInfo) {
        mIncomingPlaceAndRentInfo = incomingInfo;
        User incomingTenant = (User) incomingInfo.getSerializable("tenant");
        String address = incomingInfo.getString("address");
        String description = incomingInfo.getString("description");
        if (incomingTenant != null) {
            registerPlace(address, description, incomingTenant.getUserId(), mUser.getUserId());
        } else {
            registerPlace(address, description, 0, mUser.getUserId());
        }
    }

    @Override
    public void updateSelectPlaces(ArrayList<Place> places) {
        mPlaceAdapter.addAll(places);
    }


    @Override
    public void registerRent(int placeId) {

        double totalAmount = Double.parseDouble(Objects.requireNonNull(mIncomingPlaceAndRentInfo.getString("total_amount")));
        String dueDate = mIncomingPlaceAndRentInfo.getString("due_date");
        Rent mRent = new Rent(placeId, totalAmount, totalAmount, dueDate, false);

        mPresenter.registerRent(mRent);
    }

    @SuppressLint("SetTextI18n")
    private void manageView() {

        if (!mUser.isLandlord()) {
            mPlaceAdapter = new PlacesListAdapter(Objects.requireNonNull(getContext()));
            mListViewSelectedPlaces.setAdapter(mPlaceAdapter);

            mButtonAddPlace.setVisibility(View.GONE);
            mButtonSelectPlace.setVisibility(View.VISIBLE);
            mTextViewEnterPlaces.setText(Constants.SELECT_PLACES_WHERE_YOU_PAY_RENT);
        } else {
            mPlaceAdapter = new PlacesListAdapter(Objects.requireNonNull(getContext()));
            mListViewSelectedPlaces.setAdapter(mPlaceAdapter);
            mButtonAddPlace.setVisibility(View.VISIBLE);
            mButtonSelectPlace.setVisibility(View.GONE);
            mTextViewEnterPlaces.setText(Constants.ENTER_LANDLORD_PLACES);
        }
    }

    private void registerPlace(String address, String description, int tenantId, int landlordId) {
        Place mPlace = new Place(address, description, tenantId, landlordId);
        mPlaceAdapter.add(mPlace);
        mPresenter.registerPlace(mPlace);
    }

    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString(Constants.FONT_LIST, "1"));

        switch (selectedFont) {
            case 1:
                mTextViewEnterPlaces.setTypeface(EasyFonts.droidSerifBold(Objects.requireNonNull(getContext())));
                break;
            case 2:
                mTextViewEnterPlaces.setTypeface(EasyFonts.funRaiser(Objects.requireNonNull(getContext())));
                break;
            case 3:
                mTextViewEnterPlaces.setTypeface(EasyFonts.walkwayBold(Objects.requireNonNull(getContext())));
                break;

        }
    }
}
