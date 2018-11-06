package source.kevtimov.landlordcommunicationapp.services;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Message;

public interface MessageService {
    void sendMessage(Message message) throws IOException;
}
