package source.kevtimov.landlordcommunicationapp.views.login.myusers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class UsersAdapter  extends ArrayAdapter<User> {

    private List<User> userList = new ArrayList<User>();

    @BindView(R.id.tv_first_name)
    TextView mFirstName;

    @BindView(R.id.tv_last_name)
    TextView mLastName;

    public UsersAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public void add(User object) {
        userList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public User getItem(int index) {
        return this.userList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.users_adapter_view, parent, false);
        }

        ButterKnife.bind(this, view);

        User user = getItem(position);

        mFirstName.setText(user.getFirstName());
        mLastName.setText(user.getLastName());
        mFirstName.setTextColor(Color.WHITE);
        mLastName.setTextColor(Color.WHITE);

        return view;
    }

    private void initView() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString("font_list", "1"));

        switch (selectedFont) {
            case 1:
                mFirstName.setTypeface(EasyFonts.droidSerifBold(getContext()));
                mLastName.setTypeface(EasyFonts.droidSerifBold(getContext()));
                break;
            case 2:
                mFirstName.setTypeface(EasyFonts.funRaiser(getContext()));
                mLastName.setTypeface(EasyFonts.funRaiser(getContext()));
                break;
            case 3:
                mFirstName.setTypeface(EasyFonts.walkwayBold(getContext()));
                mLastName.setTypeface(EasyFonts.walkwayBold(getContext()));
                break;

        }
    }
}
