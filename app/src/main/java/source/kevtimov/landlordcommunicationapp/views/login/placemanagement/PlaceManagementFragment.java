package source.kevtimov.landlordcommunicationapp.views.login.placemanagement;


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

public class PlaceManagementFragment extends Fragment implements ContractsPlaceManagement.View{

    private ContractsPlaceManagement.Presenter mPresenter;
    private ContractsPlaceManagement.Navigator mNavigator;
    private List<Place> places = new ArrayList<>();
    private List<Rent> rents = new ArrayList<>();
    private ArrayAdapter<Place> mPlaceAdapter;
    private User mUser;

    @BindView(R.id.tv_enter_places)
    TextView mTextViewEnterPlaces;

    @BindView(R.id.lv_places)
    ListView mListViewSelectedPlaces;

    @BindView(R.id.btn_add_place)
    Button mButtonAddPlace;

    @BindView(R.id.btn_select_place)
    Button mButtonSelectPlace;

    @BindView(R.id.btn_ready)
    Button mButtonReady;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;


    @Inject
    public PlaceManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_place_management, container, false);

//        places = new ArrayList<>();
//        rents = new ArrayList<>();

        ButterKnife.bind(this, root);
        mPlaceAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                android.R.layout.simple_expandable_list_item_1);
        mListViewSelectedPlaces.setAdapter(mPlaceAdapter);

        if(!mUser.isLandlord()){
            manageView();
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadPlaces(places);
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getContext(),
                 error.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showPlaces(List<Place> places) {
        mPlaceAdapter.clear();
        mPlaceAdapter.addAll(places);
        mPlaceAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(getContext(), "No places found.", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void fillPlacesList(Place place) {
        this.places.add(place);
    }

    @Override
    public void fillRentsList(Rent rent) {
        this.rents.add(rent);
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void NavigateUserToHome() {
        mNavigator.navigateToHomeActivity(mUser);
    }

    @OnClick(R.id.btn_add_place)
    public void onClickAdd(View v){
        // navigate to AddPlaceActivity
    }

    @OnClick(R.id.btn_select_place)
    public void onClickSelect(View v){

        // navigate to SelectPlaceActivity
    }

    @OnClick(R.id.btn_ready)
    public void onClickReady(View v){
        if(mUser.isLandlord()){
            mPresenter.registerPlaces(places);
            mPresenter.registerRents(rents);
            mPresenter.allowNavigation();
        }else{
            //do smth with selected places(UPDATE)

            mPresenter.allowNavigation();
        }
    }

    private void manageView() {
        mButtonAddPlace.setVisibility(View.GONE);
        mButtonSelectPlace.setVisibility(View.VISIBLE);
    }
}
