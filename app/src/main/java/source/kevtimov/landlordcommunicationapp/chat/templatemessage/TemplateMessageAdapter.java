package source.kevtimov.landlordcommunicationapp.chat.templatemessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;

public class TemplateMessageAdapter extends ArrayAdapter<String> {

    @BindView(R.id.tv_templ_message)
    TextView mTextViewMessage;

    private List<String> messages = new ArrayList<String>();

    public TemplateMessageAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public void add(String object) {
        messages.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.messages.size();
    }

    @Override
    public String getItem(int index) {
        return this.messages.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.template_message_adapter_view, parent, false);
        }

        ButterKnife.bind(this, view);

        String message = getItem(position);

        mTextViewMessage.setText(message);

        return view;
    }
}
