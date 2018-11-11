package source.kevtimov.landlordcommunicationapp.services.base;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Message;

public interface MessageService {

    Message createMessage(Message message) throws IOException;

    List<Message> getMessagesByChatId(int chatId) throws IOException;
}
