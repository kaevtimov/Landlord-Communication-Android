package source.kevtimov.landlordcommunicationapp.services.base;

import java.util.List;

public interface TemplateMessageService {

    List<String> getAllTemplateMessages();

    void addTemplateMessage(String message);
}
