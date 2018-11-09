package source.kevtimov.landlordcommunicationapp.views.login.addplace;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.vstechlab.easyfonts.EasyFonts;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AddPlaceFragment extends Fragment implements ContractsAddPlace.View {


    @BindView(R.id.tv_address)
    TextView mTextViewAddress;

    @BindView(R.id.tv_description)
    TextView mTextViewDescription;

    @BindView(R.id.tv_rent_amount)
    TextView mTextViewRentAmount;

    @BindView(R.id.tv_duedate)
    TextView mTextViewDueDate;

    @BindView(R.id.tv_year)
    TextView mTextViewYear;

    @BindView(R.id.tv_month)
    TextView mTextViewMonth;

    @BindView(R.id.tv_day)
    TextView mTextViewDay;

    @BindView(R.id.tv_tenant)
    TextView mTextViewTenantName;

    @BindView(R.id.et_enter_address)
    EditText mEditTextAddress;

    @BindView(R.id.et_enter_description)
    EditText mEditTextDescription;

    @BindView(R.id.et_enter_amount)
    EditText mEditTextAmount;

    @BindView(R.id.et_enter_year)
    EditText mEditTextYear;

    @BindView(R.id.et_enter_month)
    EditText mEditTextMonth;

    @BindView(R.id.et_enter_day)
    EditText mEditTextDay;

    @BindView(R.id.progress_bar)
    CircleProgressBar mLoadingView;


    private ContractsAddPlace.Presenter mPresenter;
    private ContractsAddPlace.Navigator mNavigator;


    @Inject
    public AddPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_place, container, false);

        ButterKnife.bind(this, root);

        initFont();

        getActivity()
                .getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
    public void setPresenter(ContractsAddPlace.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsAddPlace.Navigator navigator) {
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
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
    }

    @Override
    public void navigateToPlaceManagementOnCancel() {
        mNavigator.navigateOnCancel();
    }

    @Override
    public void navigateToPlaceManagementOnSave(Bundle info) {
        mNavigator.navigateToPlaceManagementActivity(info);
    }

    @Override
    public void navigateToSelectTenant() {
        mNavigator.navigateToSelectTenant();
    }

    @OnClick(R.id.btn_save)
    public void onClickSave(View v) {
        mPresenter.checkInputInfo(mEditTextAddress.getText().toString(), mEditTextDescription.getText().toString(),
                mEditTextAmount.getText().toString(), mEditTextYear.getText().toString(), mEditTextMonth.getText().toString(),
                mEditTextDay.getText().toString());
    }

    @OnClick(R.id.btn_select_tenant)
    public void onClickSelectTenant(View v) {
        mPresenter.allowNavigateToSelectTenant();
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel(View v) {
        StyleableToast.makeText(getContext(), Constants.NO_PLACES_ADDED,
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
        mPresenter.allowNavigationOnCancel();
    }

    @Override
    public void alertDialogManagement(Bundle placeAndRentInfo) {

        FancyAlertDialog dialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle(Constants.ADD_PLACE_WARNING_MSG)
                .setBackgroundColor(Color.parseColor("#FF6600"))
                .setNegativeBtnText(Constants.CANCEL)
                .setPositiveBtnBackground(Color.parseColor("#FF6600"))
                .setPositiveBtnText(Constants.YES)
                .setNegativeBtnBackground(Color.parseColor("#FF0000"))
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_error_outline_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        StyleableToast.makeText(getContext(), Constants.ADD_PLACE_SAVED,
                                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
                        mPresenter.allowNavigationOnSave(placeAndRentInfo);
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        StyleableToast.makeText(getContext(), Constants.ADD_PLACE_CANCELED,
                                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
                    }
                })
                .build();
    }

    @Override
    public void alertForAddressConstraint() {
        StyleableToast.makeText(getContext(), Constants.ADD_PLACE_ADDRESS_CONSTRAINT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertForDescriptionConstraint() {
        StyleableToast.makeText(getContext(), Constants.ADD_PLACE_DESCRIPTION_CONSTRAINT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertForTotalAmountConstraint() {
        StyleableToast.makeText(getContext(), Constants.RENT_AMOUNT_MIN_AMOUNT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertForYearConstraint() {
        StyleableToast.makeText(getContext(), Constants.YEAR_CONSTRAINT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertForMonthConstraint() {
        StyleableToast.makeText(getContext(), Constants.MONTH_CONSTRAINT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertForDayConstraint() {
        StyleableToast.makeText(getContext(), Constants.DAY_CONSTRAINT,
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void manageTenantName(User user) {
        mTextViewTenantName.setText(Constants.TENANT + user.getFirstName() + " " + user.getLastName());
    }

    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString(Constants.FONT_LIST, "1"));

        switch (selectedFont) {
            case 1:
                mTextViewAddress.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewDescription.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewRentAmount.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewDueDate.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewYear.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewMonth.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewDay.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewTenantName.setTypeface(EasyFonts.droidSerifBold(getContext()));
                break;
            case 2:
                mTextViewAddress.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewDescription.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewRentAmount.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewDueDate.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewYear.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewMonth.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewDay.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewTenantName.setTypeface(EasyFonts.funRaiser(getContext()));
                break;
            case 3:
                mTextViewAddress.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewDescription.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewRentAmount.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewDueDate.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewYear.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewMonth.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewDay.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewTenantName.setTypeface(EasyFonts.walkwayBold(getContext()));
                break;

        }
    }
}
