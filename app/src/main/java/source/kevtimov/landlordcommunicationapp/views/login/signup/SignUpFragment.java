package source.kevtimov.landlordcommunicationapp.views.login.signup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class SignUpFragment extends Fragment implements ContractsSignUp.View {


    @BindView(R.id.tv_enter_username)
    TextView mTextViewEnterUsername;

    @BindView(R.id.tv_username_constraints)
    TextView mTextViewUsernameConstraint;

    @BindView(R.id.tv_enter_password)
    TextView mTextViewEnterPassword;

    @BindView(R.id.tv_password_constraints)
    TextView mTextViewPassConstraint;

    @BindView(R.id.tv_retype_password)
    TextView mTextViewRetypePass;

    @BindView(R.id.tv_first_name)
    TextView mTextViewEnterFirstName;

    @BindView(R.id.tv_last_name)
    TextView mTextViewEnterLastName;

    @BindView(R.id.tv_enter_email)
    TextView mTextViewEnterEmail;

    @BindView(R.id.tv_choose_type)
    TextView mTextViewEnterType;

    @BindView(R.id.et_enter_username)
    EditText mEditTextEnterUsername;

    @BindView(R.id.et_enter_password)
    EditText mEditTextEnterPass;

    @BindView(R.id.et_retype_password)
    EditText mEditTextRetypePass;

    @BindView(R.id.et_enter_first_name)
    EditText mEditTextEnterFirstName;

    @BindView(R.id.et_enter_last_name)
    EditText mEditTextEnterLastName;

    @BindView(R.id.et_enter_email)
    EditText mEditTextEnterEmail;

    @BindView(R.id.radiod_select_type)
    RadioGroup mRadioGroup;

    @BindView(R.id.btn_finish)
    Button mButtonContinue;

    @BindView(R.id.btn_finish_2)
    Button mButtonContinue2;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private RadioButton radioTypeButton;
    private ContractsSignUp.Presenter mPresenter;
    private ContractsSignUp.Navigator mNavigator;
    private Bundle mUserInfoData;
    private int radioButtonSelectedId;


    @Inject
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, root);

        if (mUserInfoData.getString("intent_purpose").equals("facebook")) {
            manageViewVisibility();
        }


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
    public void setPresenter(ContractsSignUp.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsSignUp.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void proceedToPlaceManagement(User user) {

        StyleableToast.makeText(getContext(), "Sign Up successful!",
                Toast.LENGTH_LONG, R.style.accept_login_toast)
                .show();

        mNavigator.navigateToPlaceManagement(user);
    }

    @Override
    public void setBundleWithUserInfo(Bundle userInfo) {
        this.mUserInfoData = userInfo;
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
    public void signUpFail() {
        StyleableToast.makeText(getContext(), "Signing up failed!",
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    // custom signup
    @OnClick(R.id.btn_finish)
    public void onClickFinishBottom(View root) {

        if (mEditTextEnterUsername.getText().toString().length() == 0
                || mEditTextEnterPass.getText().toString().length() == 0
                || mEditTextRetypePass.getText().toString().length() == 0
                || mEditTextEnterEmail.getText().toString().length() == 0
                || mEditTextEnterFirstName.getText().toString().length() == 0
                || mEditTextEnterLastName.getText().toString().length() == 0
                || radioButtonSelectedId < 0) {

            StyleableToast.makeText(getContext(), "Please don't leave blank information!",
                    Toast.LENGTH_LONG, R.style.reject_login_toast)
                    .show();
        } else if (mEditTextEnterUsername.getText().toString().length() < 6
                || mEditTextEnterPass.getText().toString().length() < 6
                || mEditTextRetypePass.getText().toString().length() < 6) {

            StyleableToast.makeText(getContext(), "Please pay attention on all constraints",
                    Toast.LENGTH_LONG, R.style.reject_login_toast)
                    .show();
        } else if (!mEditTextEnterPass.getText().toString().equals(mEditTextRetypePass.getText().toString())) {
            StyleableToast.makeText(getContext(), "Failed! There is difference between passwords!",
                    Toast.LENGTH_LONG, R.style.reject_login_toast)
                    .show();
        } else {
            mUserInfoData = fillBundleWithUserData();
            mPresenter.registerUser(mUserInfoData);
        }
    }

    private boolean getRadioButtonResult() {

        radioButtonSelectedId = mRadioGroup.getCheckedRadioButtonId();
        radioTypeButton = (RadioButton) Objects.requireNonNull(getActivity()).findViewById(radioButtonSelectedId);

        return !radioTypeButton.getText().equals("Tenant");
    }


    //facebook sign up
    @OnClick(R.id.btn_finish_2)
    public void onClickFinishTop(View root) {

        boolean isLandlord = getRadioButtonResult();

        if (radioButtonSelectedId < 0) {
            StyleableToast.makeText(getContext(), "Please choose type information!",
                    Toast.LENGTH_LONG, R.style.reject_login_toast)
                    .show();
        }

        mUserInfoData.putBoolean("isLandlord", isLandlord);
        mPresenter.registerUser(mUserInfoData);
    }

    private Bundle fillBundleWithUserData() {

        Bundle filledBundle = new Bundle();
        boolean isLandlord = getRadioButtonResult();


        filledBundle.putBoolean("isLandlord", isLandlord);
        filledBundle.putString("username", mEditTextEnterUsername.getText().toString());
        filledBundle.putString("password", mEditTextEnterPass.getText().toString());
        filledBundle.putString("first_name", mEditTextEnterFirstName.getText().toString());
        filledBundle.putString("last_name", mEditTextEnterLastName.getText().toString());
        filledBundle.putString("email", mEditTextEnterEmail.getText().toString());

        return filledBundle;
    }

    private void manageViewVisibility() {

        mTextViewEnterUsername.setVisibility(View.GONE);
        mTextViewUsernameConstraint.setVisibility(View.GONE);
        mTextViewEnterPassword.setVisibility(View.GONE);
        mTextViewPassConstraint.setVisibility(View.GONE);
        mTextViewRetypePass.setVisibility(View.GONE);
        mTextViewEnterFirstName.setVisibility(View.GONE);
        mTextViewEnterLastName.setVisibility(View.GONE);
        mTextViewEnterEmail.setVisibility(View.GONE);
        mEditTextEnterUsername.setVisibility(View.GONE);
        mEditTextEnterPass.setVisibility(View.GONE);
        mEditTextRetypePass.setVisibility(View.GONE);
        mEditTextEnterFirstName.setVisibility(View.GONE);
        mEditTextEnterLastName.setVisibility(View.GONE);
        mEditTextEnterEmail.setVisibility(View.GONE);
        mButtonContinue.setVisibility(View.GONE);
        mButtonContinue2.setVisibility(View.VISIBLE);
    }
}
