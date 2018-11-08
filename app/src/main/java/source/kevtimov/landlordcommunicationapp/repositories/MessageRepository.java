package source.kevtimov.landlordcommunicationapp.repositories;

import com.google.gson.JsonObject;

import java.io.IOException;

import source.kevtimov.landlordcommunicationapp.models.Message;

public interface MessageRepository {
    Message sendMessage(Message message) throws IOException;

    Message receiveMessage() throws IOException;
}
