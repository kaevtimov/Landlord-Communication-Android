package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import com.muddzdev.styleabletoast.StyleableToast;

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

public class PlaceManagementFragment extends Fragment implements ContractsPlaceManagement.View {

    private ContractsPlaceManagement.Presenter mPresenter;
    private ContractsPlaceManagement.Navigator mNavigator;
    private ArrayAdapter<String> mPlaceAdapter;
    private User mUser;  // user incoming from previous activity
    private Bundle incomingPlaceAndRentInfo;

    @BindView(R.id.tv_enter_places)
    TextView mTextViewEnterPlaces;

    @BindView(R.id.lv_places)
    ListView mListViewSelectedPlaces;

    @BindView(R.id.btn_add_place)
    Button mButtonAddPlace;

    @BindView(R.id.btn_select_place)
    Button mButtonSelectPlace;

    @BindView(R.id.progress_bar_manage)
    ProgressBar mProgressBar;


    @Inject
    public PlaceManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_place_management, container, false);

        ButterKnife.bind(this, root);
        mPlaceAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                android.R.layout.simple_expandable_list_item_1);
        mListViewSelectedPlaces.setAdapter(mPlaceAdapter);

        manageView();

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
        this.mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.mProgressBar.setVisibility(View.GONE);
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
    public void navigateUserToHome() {
        mNavigator.navigateToHomeActivity(mUser);
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
        StyleableToast.makeText(getContext(), "Adding place failed!",
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


    @OnClick(R.id.btn_home)
    public void onClickHome(View v) {
        
        mPresenter.allowToHomeActivity();
    }


    @Override
    public void manageIncomingInformation(Bundle incomingInfo) {
        incomingPlaceAndRentInfo = incomingInfo;
        User incoming = (User) incomingInfo.getSerializable("tenant");
        String address = incomingInfo.getString("address");
        String description = incomingInfo.getString("description");
        double totalAmount = Double.parseDouble(Objects.requireNonNull(incomingInfo.getString("total_amount")));
        String dueDate = incomingInfo.getString("due_date");
        StringBuilder adapterPlaceInfoView = new StringBuilder();
        adapterPlaceInfoView.append("Address: ").append(address).append("\n").append("Tenant: ").append(incoming.getFirstName())
                .append(" ").append(incoming.getLastName());
        mPlaceAdapter.add(adapterPlaceInfoView.toString());

        registerPlace(address, description, incoming.getUserId(), mUser.getUserId());
    }


    private void registerPlace(String address, String description, int tenantId, int landlordId) {
        Place mPlace = new Place(address, description, tenantId, landlordId);
        mPresenter.registerPlace(mPlace);
    }

    @Override
    public void registerRent(int placeId) {

        double totalAmount = Double.parseDouble(Objects.requireNonNull(incomingPlaceAndRentInfo.getString("total_amount")));
        String dueDate = incomingPlaceAndRentInfo.getString("due_date");
        Rent mRent = new Rent(placeId, totalAmount, totalAmount, dueDate, false);

        mPresenter.registerRent(mRent);
    }

    @SuppressLint("SetTextI18n")
    private void manageView() {

        if (!mUser.isLandlord()){
            mButtonAddPlace.setVisibility(View.GONE);
            mButtonSelectPlace.setVisibility(View.VISIBLE);
            mTextViewEnterPlaces.setText("Please select places where you pay rent");
        } else{
            mButtonAddPlace.setVisibility(View.VISIBLE);
            mButtonSelectPlace.setVisibility(View.GONE);

            mTextViewEnterPlaces.setText("Please enter your places as landlord");
        }
    }
}
