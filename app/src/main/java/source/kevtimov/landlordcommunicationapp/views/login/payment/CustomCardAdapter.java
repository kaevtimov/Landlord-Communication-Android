package source.kevtimov.landlordcommunicationapp.views.login.payment;


import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;

public class CustomCardAdapter<Card> extends BaseAdapter {


    @BindView(R.id.iv_card)
    ImageView mImageViewCards;

    @BindView(R.id.tv_card)
    TextView mTextViewCard;

    @BindView(R.id.check_box)
    CheckBox mCardsBox;


    private SparseBooleanArray mSparseBooleanArray;
    private LayoutInflater mInflater;
    private ArrayList<Card> mCardsList;
    private Context mContext;

    public CustomCardAdapter(Context context, ArrayList<Card> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mSparseBooleanArray = new SparseBooleanArray();
        mCardsList = new ArrayList<>();
        this.mCardsList = list;
    }



    @Override
    public int getCount() {
        return mCardsList.size();
    }


    @Override
    public Object getItem(int position) {
        return mCardsList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_card_adapter_layout, null);
        }

        ButterKnife.bind(this, view);

        mTextViewCard.setText(mCardsList.get(position).toString());
        mCardsBox.setTag(position);
        mCardsBox.setChecked(mSparseBooleanArray.get(position));
        mCardsBox.setOnCheckedChangeListener(checkBoxOnChangeListener);

        return view;
    }


    CompoundButton.OnCheckedChangeListener checkBoxOnChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }
    };

    public ArrayList<Card> getCheckedCards() {

        ArrayList<Card> cardsSelected = new ArrayList<>();

        for (int i = 0; i < mCardsList.size(); i++) {
            if (mSparseBooleanArray.get(i)) {
                cardsSelected.add(mCardsList.get(i));
            }
        }
        return cardsSelected;
    }
}
