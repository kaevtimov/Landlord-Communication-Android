package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.utils.drawer.BaseDrawer;

public class ChatRoomActivity extends BaseDrawer {
    private static final long IDENTIFIER = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
    }

    @Override
    protected long getIdentifier() {
        return 0;
    }

    @Override
    protected Toolbar getToolbar() {
        return null;
    }
}
