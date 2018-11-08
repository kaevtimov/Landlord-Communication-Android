package source.kevtimov.landlordcommunicationapp.services;

import com.google.gson.JsonObject;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Message;

public interface MessageService {
    Message sendMessage(Message message) throws IOException;

    Message receiveMessage() throws IOException;
}
