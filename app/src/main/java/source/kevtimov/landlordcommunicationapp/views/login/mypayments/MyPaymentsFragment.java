package source.kevtimov.landlordcommunicationapp.views.login.mypayments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Payment;

public class MyPaymentsFragment extends Fragment implements ContractsMyPayments.View{

    @BindView(R.id.iv_payment)
    ImageView mImageViewMoney;

    @BindView(R.id.lv_payments)
    ListView mListViewPayments;

    @BindView(R.id.progressBar)
    CircleProgressBar mProgressBar;


    private ContractsMyPayments.Presenter mPresenter;
    private MyPaymentsAdapter mPaymentAdapter;


    @Inject
    public MyPaymentsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_my_payments, container, false);
        ButterKnife.bind(this, root);

        mPaymentAdapter = new MyPaymentsAdapter(Objects.requireNonNull(getContext()));
        mListViewPayments.setAdapter(mPaymentAdapter);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadPayments();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsMyPayments.Presenter presenter) {
        this.mPresenter = presenter;
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
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @Override
    public void showPayments(List<Payment> payments) {
        mPaymentAdapter.clear();
        mPaymentAdapter.addAll(payments);
        mPaymentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyList() {
        StyleableToast.makeText(getContext(), "NO PAYMENTS",
                Toast.LENGTH_LONG, R.style.reject_login_toast).show();
    }

    @OnItemClick(R.id.lv_payments)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Payment payment = mPaymentAdapter.getItem(position);

        showInfoDialog(payment);
    }

    private void showInfoDialog(Payment payment) {
        FancyAlertDialog dialog = new FancyAlertDialog.Builder(getActivity())
                .setTitle("Information")
                .setBackgroundColor(Color.parseColor("#6600CC"))
                .setMessage("Payment amount: " + payment.getAmount() + "\n" + "Date: " + payment.getDate() + "\n" + "Card number: "
                        + payment.getCard().getCardNumber() + "\n" + "Place: " + payment.getPlace().getAddress() + "\n"
                        + "Tenant: " + payment.getUser().getFirstName() + " " + payment.getUser().getLastName() + "\n")
                .setPositiveBtnBackground(Color.parseColor("#6600CC"))
                .setPositiveBtnText("OK")
                .setAnimation(Animation.POP)
                .setIcon(R.drawable.ic_monetization_on_black_24dp, Icon.Visible)
                .build();
    }
}
