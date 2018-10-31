package source.kevtimov.landlordcommunicationapp.views.login.addcard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;

public class AddCardFragment extends Fragment implements ContractsAddCard.View {


    @BindView(R.id.iv_card)
    ImageView mImageView;

    @BindView(R.id.tv_enter_type)
    TextView mTypeTextView;

    @BindView(R.id.tv_enter_brand)
    TextView mBrandTextView;

    @BindView(R.id.tv_enter_card_number)
    TextView mNumberTextView;

    @BindView(R.id.tv_enter_cvv)
    TextView mCVVTextView;

    @BindView(R.id.et_card_type)
    EditText mTypeEditText;

    @BindView(R.id.et_card_brand)
    EditText mBrandEditText;

    @BindView(R.id.et_card_number)
    EditText mNumberEditText;

    @BindView(R.id.et_card_cvv)
    EditText mCVVEditText;


    private ContractsAddCard.Presenter mPresenter;
    private ContractsAddCard.Navigator mNavigator;

    @Inject
    public AddCardFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_card, container, false);
        ButterKnife.bind(this, root);

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
    public void setPresenter(ContractsAddCard.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsAddCard.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
    }

    @Override
    public void navigateBack() {
        StyleableToast.makeText(getContext(), "Card added successfully!",
                Toast.LENGTH_LONG, R.style.accept_login_toast).show();
        mNavigator.navigateBack();
    }

    @Override
    public void alertForConstraints() {
        StyleableToast.makeText(getContext(), "Warning! Card number must be exactly 16 digits and CVV number must be exactly 3 digits!",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void alertEmptyFields() {
        StyleableToast.makeText(getContext(), "Warning! Please Don't leave empty fields!",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @OnClick(R.id.btn_add)
    public void onClickAddCard(View v){
        String cardType = mTypeEditText.getText().toString();
        String cardBrand = mBrandEditText.getText().toString();
        String cardNumber = mNumberEditText.getText().toString();
        String cvvNumber = mCVVEditText.getText().toString();

        mPresenter.addCard(cardType, cardBrand,cardNumber,cvvNumber);
    }
}
