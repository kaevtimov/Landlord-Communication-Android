package source.kevtimov.landlordcommunicationapp.sqlite;

import java.util.List;

public interface TemplateMessageSQLite {

    List<String> getAllTemplateMessages();

    boolean addTemplateMessage(String message);
}
