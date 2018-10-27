package source.kevtimov.landlordcommunicationapp.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class ChatListAdapter extends ArrayAdapter<User> {
    @BindView(R.id.tv_name)
    TextView mFullNameTextView;

    @Inject
    public ChatListAdapter(@NonNull Context context) {
        super(context,-1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.chat_user_item, parent, false);
        }
        ButterKnife.bind(this, view);

        User user = getItem(position);
        String fullName = "placeholder";
        if (user != null) {
            fullName = user.getFirstName() + " " + user.getLastName();
        }
        mFullNameTextView.setText(fullName);

        return view;
    }
}
