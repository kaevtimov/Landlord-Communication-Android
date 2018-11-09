package source.kevtimov.landlordcommunicationapp.views.login.mypayments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import source.kevtimov.landlordcommunicationapp.utils.Constants;

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

        mTextViewAmount.setText(Constants.AMOUNT + Objects.requireNonNull(payment).getAmount());
        mTextViewDate.setText(Constants.DATE + payment.getDate());

        initFont();

        return view;
    }
    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString(Constants.FONT_LIST, "1"));

        switch (selectedFont) {
            case 1:
                mTextViewAmount.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mTextViewDate.setTypeface(EasyFonts.droidSerifBold(getContext()));
                break;
            case 2:
                mTextViewAmount.setTypeface(EasyFonts.funRaiser(getContext()));
                mTextViewDate.setTypeface(EasyFonts.funRaiser(getContext()));
                break;
            case 3:
                mTextViewAmount.setTypeface(EasyFonts.walkwayBold(getContext()));
                mTextViewDate.setTypeface(EasyFonts.walkwayBold(getContext()));
                break;

        }
    }
}
