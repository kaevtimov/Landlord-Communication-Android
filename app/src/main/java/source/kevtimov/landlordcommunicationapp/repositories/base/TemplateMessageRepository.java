package source.kevtimov.landlordcommunicationapp.repositories.base;

import java.util.List;

public interface TemplateMessageRepository {

    List<String> getAllTemplateMessages();

    void addTemplateMessage(String message);
}
