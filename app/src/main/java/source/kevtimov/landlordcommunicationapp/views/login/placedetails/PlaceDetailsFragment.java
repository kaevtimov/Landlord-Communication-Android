package source.kevtimov.landlordcommunicationapp.views.login.placedetails;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.vstechlab.easyfonts.EasyFonts;

import org.apache.http.params.CoreConnectionPNames;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

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

    @BindView(R.id.btn_edit)
    Button mEditButton;

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

        initFont();

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
        StyleableToast.makeText(getContext(), Constants.EMPTY,
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
            mLandlordInfo.setText(Constants.LANDLORD + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "\n"
                    + Constants.USERNAME + loggedInUser.getUsername());
            mTenantInfo.setText(Constants.TENANT + notLoggedInUser.getFirstName() + " " + notLoggedInUser.getLastName() + "\n"
                    + Constants.USERNAME + notLoggedInUser.getUsername());
            mPayButton.setVisibility(View.GONE);

            mPresenter.getUnpaidRent();
        }else{
            mLandlordInfo.setText(Constants.LANDLORD + notLoggedInUser.getFirstName() + " " + notLoggedInUser.getLastName() + "\n"
                    + Constants.USERNAME + notLoggedInUser.getUsername());
            mTenantInfo.setText(Constants.TENANT + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "\n"
                    + Constants.USERNAME + loggedInUser.getUsername());
            mPresenter.getUnpaidRent();
        }
        mAddress.setText(Constants.ADDRESS + mPlace.getAddress());
        mDescription.setText(Constants.DESCRIPTION + mPlace.getDescription());
    }

    @OnClick(R.id.btn_pay)
    public void onPayClick(View v){
        mPresenter.allowNavigationToPayRent();
    }

    @OnClick(R.id.btn_edit)
    public void onEditClick(View v) {
        showDialog();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void manageViewsWithNoTenant(User mLogInUser, Place mPlace) {
        mLandlordInfo.setText(Constants.LANDLORD + mLogInUser.getFirstName() + " " + mLogInUser.getLastName() + "\n"
                + Constants.USERNAME + mLogInUser.getUsername());
        mPayButton.setVisibility(View.GONE);

        mRentInfo.setText(Constants.NO_RENT_INFORMATION);

        mAddress.setText(Constants.ADDRESS + mPlace.getAddress());
        mDescription.setText(Constants.DESCRIPTION + mPlace.getDescription());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void viewRent(Rent rent) {
        mRentInfo.setText(Constants.DUE_DATE_RENT + rent.getDueDate() + "\n" + Constants.TOTAL_AMOUNT + rent.getTotalAmount() + Constants.LEVA
                + Constants.REMAINING_AMOUNT + rent.getRemainingAmount() + Constants.LEVA + Constants.IS_RENT_PAID + rent.isPaid());
        mPresenter.setRent(rent);
    }

    @Override
    public void viewEmptyRent() {
        mRentInfo.setText(Constants.NO_RENT_INFORMATION);
        mPayButton.setVisibility(View.GONE);
        mEditButton.setVisibility(View.GONE);
    }

    @Override
    public void alertForBlankAmountInfo() {
        StyleableToast.makeText(getContext(), Constants.ENTER_AMOUNT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertForAmountConstraint() {
        StyleableToast.makeText(getContext(), Constants.EDIT_RENT_CONSTRAINTS,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString(Constants.FONT_LIST, "1"));

        switch (selectedFont) {
            case 1:
                mAddress.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mDescription.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTenantInfo.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mLandlordInfo.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mRentInfo.setTypeface(EasyFonts.droidSerifBold(getContext()));
                break;
            case 2:
                mAddress.setTypeface(EasyFonts.funRaiser(getContext()));
                mDescription.setTypeface(EasyFonts.funRaiser(getContext()));
                mTenantInfo.setTypeface(EasyFonts.funRaiser(getContext()));
                mLandlordInfo.setTypeface(EasyFonts.funRaiser(getContext()));
                mRentInfo.setTypeface(EasyFonts.funRaiser(getContext()));
                break;
            case 3:
                mAddress.setTypeface(EasyFonts.walkwayBold(getContext()));
                mDescription.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTenantInfo.setTypeface(EasyFonts.walkwayBold(getContext()));
                mLandlordInfo.setTypeface(EasyFonts.walkwayBold(getContext()));
                mRentInfo.setTypeface(EasyFonts.walkwayBold(getContext()));
                break;

        }
    }

    private void showDialog() {
        AlertDialog editPaymentDialog = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_payment_dialog, null);

        EditText mAmountEditText = dialogView.findViewById(R.id.et_amount);
        FloatingActionButton mButtonCancel = dialogView.findViewById(R.id.btn_cancel);
        FloatingActionButton mButtonSave = dialogView.findViewById(R.id.btn_save);
        TextView mTextViewEnterAmount = dialogView.findViewById(R.id.tv_enter_amount);


        editPaymentDialog.setView(dialogView);
        editPaymentDialog.show();

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPaymentDialog.dismiss();
            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.editRentAmount(mAmountEditText.getText().toString());

                editPaymentDialog.dismiss();
            }
        });
    }
}
