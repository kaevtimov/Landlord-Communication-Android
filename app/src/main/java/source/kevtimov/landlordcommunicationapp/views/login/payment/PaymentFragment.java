package source.kevtimov.landlordcommunicationapp.views.login.payment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;

public class PaymentFragment extends Fragment implements ContractsPayments.View {

    @BindView(R.id.btn_view_cards)
    Button mButtonCadsView;

    @BindView(R.id.tv_date)
    TextView mDate;

    @BindView(R.id.tv_total_amount)
    TextView mTotalAmount;

    @BindView(R.id.tv_remaining_amount)
    TextView mRemainingAmount;

    @BindView(R.id.tv_custom_amount)
    TextView mCustomAmount;

    @BindView(R.id.tv_choose_card)
    TextView mChooseCard;

    @BindView(R.id.tv_amount_constraint)
    TextView mAmountConstraint;

    @BindView(R.id.lv_cards)
    ListView mCards;

    @BindView(R.id.et_amount)
    EditText mEditTextAmount;

    private ContractsPayments.Presenter mPresenter;
    private ContractsPayments.Navigator mNavigator;
    private CustomCardAdapter<Card> mCardAdapter;
    private ArrayList<Card> mCardsList;
    private double mEnteredAmount;


    @Inject
    public PaymentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_payment, container, false);
        ButterKnife.bind(this, root);

        getActivity()
                .getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mCardsList = new ArrayList<>();
        mCardAdapter = new CustomCardAdapter<>(getContext(), mCardsList);
        mCards.setAdapter(mCardAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadRent();
        mPresenter.loadCards();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsPayments.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsPayments.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void alertNotEnoughMoney() {
        StyleableToast.makeText(getContext(), "Sorry! Not enough money in card!",
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void alertEnteredAmountBiggerThanRemaining() {
        StyleableToast.makeText(getContext(), "Warning! Entered amount is bigger than remaining!",
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void navigateToDetailsActivity() {
        mNavigator.navigateToMyPlacesActivity();
    }

    @Override
    public void navigateToCardAdd() {
        mNavigator.navigateToAddCard();
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void manageCardView(List<Card> cards) {
        if (cards.size() > 0) {
            mCardsList.clear();
            mCardsList.addAll(cards);
            mCardAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void manageRentView(Rent rent) {
        mPresenter.setRent(rent);
        Date current = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String paymentDate = format.format(current);
        mDate.setText("Date: " + paymentDate);
        mTotalAmount.setText("Total amount: " + String.valueOf(rent.getTotalAmount()));
        mRemainingAmount.setText("Rem. amount: " + String.valueOf(rent.getRemainingAmount()));
    }

    @OnClick(R.id.btn_finish)
    public void onClickFinish(View v) {

        if (mCardAdapter != null) {
            ArrayList<Card> mArrayCards = mCardAdapter.getCheckedCards();
            if (mArrayCards.size() > 1 || mArrayCards.size() == 0) {
                StyleableToast.makeText(getContext(), "Please choose one card!",
                        Toast.LENGTH_LONG, R.style.reject_login_toast)
                        .show();
            } else if (mEditTextAmount.getText().toString().length() == 0) {
                StyleableToast.makeText(getContext(), "Please enter amount!",
                        Toast.LENGTH_LONG, R.style.reject_login_toast)
                        .show();
            } else if (Double.parseDouble(mEditTextAmount.getText().toString()) < 20) {
                StyleableToast.makeText(getContext(), "Min available amount is 20.0 leva!",
                        Toast.LENGTH_LONG, R.style.reject_login_toast)
                        .show();
            } else {
                mEnteredAmount = Double.parseDouble(mEditTextAmount.getText().toString());
                mPresenter.setCard(mArrayCards.get(0));
                alertDialogManagement();
            }
        } else {
            alertDialogNoCard();
        }
    }

    @OnClick(R.id.btn_add_card)
    public void onClickAddCard(View v) {
        mPresenter.allowNavigateToCardAdd();
    }

    @OnClick(R.id.btn_view_cards)
    public void onClickViewCards(View v) {
        mButtonCadsView.setVisibility(View.GONE);
        mCards.setVisibility(View.VISIBLE);
    }

    private void alertDialogNoCard() {
        FancyAlertDialog dialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle("WARNING")
                .setBackgroundColor(Color.parseColor("#FF6600"))
                .setMessage("PLEASE CHOOSE A CARD OR ADD ONE!")
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_error_outline_black_24dp, Icon.Visible)
                .build();
    }

    private void alertDialogManagement() {
        FancyAlertDialog dialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle("WARNING")
                .setBackgroundColor(Color.parseColor("#FF6600"))
                .setMessage("ARE YOU SURE? THE PAYMENT WILL BE PROCESSED")
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
                        StyleableToast.makeText(getContext(), "PAYMENT MADE!",
                                Toast.LENGTH_LONG, R.style.accept_login_toast).show();

                        // presenter manage payment
                        mPresenter.managePayment(mEnteredAmount);
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
