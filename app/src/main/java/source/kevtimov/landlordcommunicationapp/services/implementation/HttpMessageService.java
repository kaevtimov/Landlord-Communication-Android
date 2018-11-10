package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.repositories.base.MessageRepository;
import source.kevtimov.landlordcommunicationapp.services.base.MessageService;

public class HttpMessageService implements MessageService {


    private MessageRepository chatRepository;

    public HttpMessageService(MessageRepository repository) {
        this.chatRepository = repository;
    }

    @Override
    public Message createMessage(Message message) throws IOException {
        return  chatRepository.createMessage(message);
    }

    @Override
    public List<Message> getMessagesByChatId(int chatId) throws IOException {
        return chatRepository.getMessagesByChatId(chatId);
    }
}
