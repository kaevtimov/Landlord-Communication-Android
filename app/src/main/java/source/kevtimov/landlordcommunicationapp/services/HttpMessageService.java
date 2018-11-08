package source.kevtimov.landlordcommunicationapp.services;

import com.google.gson.JsonObject;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.repositories.MessageRepository;

public class HttpMessageService implements MessageService {
    private MessageRepository mMessageRepository;

    public HttpMessageService(MessageRepository mMessageRepository) {
        this.mMessageRepository = mMessageRepository;
    }

    @Override
    public Message sendMessage(Message message) throws IOException {
        return mMessageRepository.sendMessage(message);
    }

    @Override
    public Message receiveMessage() throws IOException {
        return mMessageRepository.receiveMessage();
    }
}
