package source.kevtimov.landlordcommunicationapp.chat;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class ChatActivity extends BaseDrawer {
    public static final long IDENTIFIER = 966;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }
}
