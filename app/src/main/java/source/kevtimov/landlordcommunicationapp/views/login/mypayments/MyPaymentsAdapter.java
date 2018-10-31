package source.kevtimov.landlordcommunicationapp.views.login.mypayments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Payment;

public class MyPaymentsAdapter extends ArrayAdapter<Payment> {

    @BindView(R.id.iv_money)
    ImageView mImageViewMoney;

    @BindView(R.id.tv_date)
    TextView mTextViewDate;

    @BindView(R.id.tv_amount)
    TextView mTextViewAmount;

    public MyPaymentsAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_my_payments_adapter, parent, false);
        }
        ButterKnife.bind(this, view);


        Payment payment = getItem(position);
        mTextViewAmount.setText("Amount: " + Objects.requireNonNull(payment).getAmount());
        mTextViewAmount.setTypeface(EasyFonts.droidSerifBold(getContext()));

        mTextViewDate.setText("Date: " + payment.getDate());
        mTextViewDate.setTypeface(EasyFonts.droidSerifBold(getContext()));

        return view;
    }
}
