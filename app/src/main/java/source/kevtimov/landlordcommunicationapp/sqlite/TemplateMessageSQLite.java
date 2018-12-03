package source.kevtimov.landlordcommunicationapp.sqlite;

import android.database.Cursor;

import java.util.List;

public interface TemplateMessageSQLite {

    Cursor getAllTemplateMessages();

    boolean addTemplateMessage(String message);
}
