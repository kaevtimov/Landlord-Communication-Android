package source.kevtimov.landlordcommunicationapp.views.login.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.muddzdev.styleabletoast.StyleableToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

import static com.facebook.GraphRequest.TAG;

public class LoginFragment extends Fragment implements ContractsLogin.View {

    private ContractsLogin.Presenter mPresenter;
    private ContractsLogin.Navigator mNavigator;
    private CallbackManager mFacebookCallbackManager;
    //    private String mUserName;
//    private String mUserFirstName;
//    private String mUserLastName;
//    private String mUserEmail;
//    private String mUserProfPic;
    private Bundle mFacebookData;

    @BindView(R.id.fb_login_button)
    LoginButton mFacebookButton;

    @BindView(R.id.tv_username)
    TextView mTextViewUsername;

    @BindView(R.id.tv_password)
    TextView mTextViewPassword;

    @BindView(R.id.et_username)
    EditText mEditTextUsername;

    @BindView(R.id.et_password)
    EditText mEditTextPassword;

    @BindView(R.id.loading_bar)
    ProgressBar mLoadingView;


    private String mEmailFacebook;
    private String mFacebookFirstName;
    private String mFacebookLastName;
    private String mProfPicture;
    private String mUserEmail;


    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);


        ButterKnife.bind(this, root);
        mFacebookButton.setFragment(this);

        getActivity()
                .getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setUpFacebookLogin();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //mPresenter.unsubscribe();
    }


    @Override
    public void setPresenter(ContractsLogin.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
        mTextViewUsername.setVisibility(View.INVISIBLE);
        mTextViewPassword.setVisibility(View.INVISIBLE);
        mEditTextUsername.setVisibility(View.INVISIBLE);
        mEditTextPassword.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingView.setVisibility(View.INVISIBLE);
        mTextViewUsername.setVisibility(View.VISIBLE);
        mTextViewPassword.setVisibility(View.VISIBLE);
        mEditTextUsername.setVisibility(View.VISIBLE);
        mEditTextPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void setNavigator(ContractsLogin.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
    }

    @Override
    public void cancelLogin() {

        StyleableToast.makeText(getContext(), "Incorrect username or password!",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void welcomeUser(User user) {

        StyleableToast.makeText(getContext(), "WELCOME, " + user.getFirstName() + " " + user.getLastName() + " !",
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();

        mNavigator.navigateToHome(user);
    }

    @Override
    public void alertUserForBlankInfo() {
        StyleableToast.makeText(getContext(), "Username and password cannot be empty!",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertUserForLengthConstraints() {
        StyleableToast.makeText(getContext(), "Username and password length should be bigger than 6 characters!",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void facebookRegisterAlert() {
        StyleableToast.makeText(getContext(), "Please add additional info about you.",
                Toast.LENGTH_LONG, R.style.facebook_login_toast).show();

        Bundle fbInfo = new Bundle();
        fbInfo.putString("intent_purpose", "facebook");
        fbInfo.putString("fb_first_name", mFacebookFirstName);
        fbInfo.putString("fb_last_name", mFacebookLastName);
        fbInfo.putString("fb_email", mEmailFacebook);
        fbInfo.putString("fb_prof_pic", mProfPicture);
        fbInfo.putString("fb_username", mEmailFacebook);

        mNavigator.navigateToSignUp(fbInfo);
    }

    @Override
    public void proceedToSignUp() {
        Bundle userInfo = new Bundle();
        userInfo.putString("intent_purpose", "custom");
        mNavigator.navigateToSignUp(userInfo);
    }


    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        String enteredUsername = mEditTextUsername.getText().toString();
        String enteredPassword = mEditTextPassword.getText().toString();
        mPresenter.checkLogin(enteredUsername, enteredPassword);
    }

    @OnClick(R.id.btn_signup)
    public void onClickSignUp() {
        mPresenter.allowSignUp();
    }

    private void setUpFacebookLogin() {
        mFacebookCallbackManager = CallbackManager.Factory.create();

        //for reading permission
        mFacebookButton.setReadPermissions(Arrays.asList("email"));
        mFacebookButton.setFragment(this);

        mFacebookButton.registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        String accessToken = loginResult.getAccessToken().getToken();

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject jsonObject,
                                                            GraphResponse response) {

                                        // Getting FB User Data
                                        Bundle facebookData = getFacebookData(jsonObject);
                                        mFacebookFirstName = facebookData.getString("first_name");
                                        mFacebookLastName = facebookData.getString("last_name");
                                        mEmailFacebook = facebookData.getString("email");
                                        mUserEmail = facebookData.getString("email");
                                        mProfPicture = facebookData.getString("profile_pic");
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,email,gender");
                        request.setParameters(parameters);
                        request.executeAsync();

                        mPresenter.verifyFacebookLogin(mUserEmail);
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "Login attempt cancelled.");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        error.printStackTrace();
                        Log.d(TAG, "Login attempt failed.");
                    }
                }
        );

    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));

        } catch (Exception e) {
            Log.d(TAG, "BUNDLE Exception : " + e.toString());
        }

        return bundle;
    }
}
