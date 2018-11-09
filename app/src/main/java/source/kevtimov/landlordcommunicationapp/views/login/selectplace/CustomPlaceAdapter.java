package source.kevtimov.landlordcommunicationapp.views.login.selectplace;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vstechlab.easyfonts.EasyFonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;


public class CustomPlaceAdapter<Place> extends BaseAdapter {


    @BindView(R.id.iv_house)
    ImageView mImageViewPlaces;

    @BindView(R.id.tv_place_addresses)
    TextView mTextViewPlacesAddress;

    @BindView(R.id.check_box)
    CheckBox mPlacesBox;


    private SparseBooleanArray mSparseBooleanArray;
    private LayoutInflater mInflater;
    private ArrayList<Place> mPlacesList;
    private Context mContext;

    public CustomPlaceAdapter(Context context, ArrayList<Place> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mSparseBooleanArray = new SparseBooleanArray();
        mPlacesList = new ArrayList<>();
        this.mPlacesList = list;
    }



    @Override
    public int getCount() {
        return mPlacesList.size();
    }


    @Override
    public Object getItem(int position) {
        return mPlacesList.get(position);
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
            view = mInflater.inflate(R.layout.custom_place_adapter_layout, null);
        }

        ButterKnife.bind(this, view);

        mTextViewPlacesAddress.setText(mPlacesList.get(position).toString());
        mPlacesBox.setTag(position);
        mPlacesBox.setChecked(mSparseBooleanArray.get(position));
        mPlacesBox.setOnCheckedChangeListener(checkBoxOnChangeListener);
        initFont();

        return view;
    }


    OnCheckedChangeListener checkBoxOnChangeListener = new OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
        }
    };

    public ArrayList<Place> getCheckedPlaces() {

        ArrayList<Place> placesSelected = new ArrayList<>();

        for (int i = 0; i < mPlacesList.size(); i++) {
            if (mSparseBooleanArray.get(i)) {
                placesSelected.add(mPlacesList.get(i));
            }
        }
        return placesSelected;
    }

    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        int selectedFont = Integer.parseInt(sharedPreferences.getString("font_list", "1"));

        switch (selectedFont) {
            case 1:
                mTextViewPlacesAddress.setTypeface(EasyFonts.droidSerifBold(mContext));
                break;
            case 2:
                mTextViewPlacesAddress.setTypeface(EasyFonts.funRaiser(mContext));
                break;
            case 3:
                mTextViewPlacesAddress.setTypeface(EasyFonts.walkwayBold(mContext));
                break;

        }
    }
}
