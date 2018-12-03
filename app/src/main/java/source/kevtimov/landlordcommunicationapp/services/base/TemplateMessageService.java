package source.kevtimov.landlordcommunicationapp.services.base;

import android.database.Cursor;

import java.util.List;

public interface TemplateMessageService {

    Cursor getAllTemplateMessages();

    void addTemplateMessage(String message);
}
