package source.kevtimov.landlordcommunicationapp.repositories.base;

import android.database.Cursor;

import java.util.List;

public interface TemplateMessageRepository {

    Cursor getAllTemplateMessages();

    void addTemplateMessage(String message);
}
