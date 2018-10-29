package source.kevtimov.landlordcommunicationapp.views.login.selectplace;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;

public class SelectPlaceFragment extends Fragment implements ContractsSelectPlace.View{


    @BindView(R.id.tv_select_place)
    TextView mtextViewSelectPlace;

    @BindView(R.id.lv_places_no_tenant)
    ListView mListViewNoTenants;

    @BindView(R.id.progress_bar)
    CircleProgressBar mLoadingView;




    private ContractsSelectPlace.Navigator mNavigator;
    private ContractsSelectPlace.Presenter mPresenter;
    private CustomPlaceAdapter<Place> mPlaceAdapter;
    private ArrayList<Place> mPlaces;


    @Inject
    public SelectPlaceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_select_place, container, false);

        ButterKnife.bind(this, root);

        mPlaces = new ArrayList<>();
        mPlaceAdapter = new CustomPlaceAdapter<>(getContext(), mPlaces);
        mListViewNoTenants.setAdapter(mPlaceAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.getAllPlacesWhereNoTenant();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsSelectPlace.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsSelectPlace.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void navigateToPlaceManagement(ArrayList<Place> mArrayPlaces) {
        mNavigator.navigateToPlaceManagementActivity(mArrayPlaces);
    }

    @Override
    public void showEmptyList() {
        StyleableToast.makeText(getContext(), "No places found!",
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void showPlaces(List<Place> places) {
        mPlaces.clear();
        mPlaces.addAll(places);
        mPlaceAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_ready)
    public void onClickReady(View v){

        if(mPlaceAdapter != null) {
            ArrayList<Place> mArrayPlaces = mPlaceAdapter.getCheckedPlaces();
            alertDialogManagement(mArrayPlaces);
        } else{
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    private void alertDialogManagement(ArrayList<Place> mPlaces) {

        FancyAlertDialog dialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle("WARNING")
                .setBackgroundColor(Color.parseColor("#FF6600"))
                .setMessage("ARE YOU SURE? FOLLOWING CHANGES WILL BE SAVE TO YOUR ACCOUNT?")
                .setNegativeBtnText("Cancel")
                .setPositiveBtnBackground(Color.parseColor("#FF6600"))
                .setPositiveBtnText("Yes")
                .setNegativeBtnBackground(Color.parseColor("#FF0000"))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_error_outline_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        StyleableToast.makeText(getContext(), "SAVED!",
                                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
                        mPresenter.navigateBackToPlaceManagement(mPlaces);
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        StyleableToast.makeText(getContext(),"CANCELED",
                                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
                        Objects.requireNonNull(getActivity()).finish();
                    }
                })
                .build();
    }
}
