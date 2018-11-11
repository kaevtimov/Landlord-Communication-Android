package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.ChatSession;
import source.kevtimov.landlordcommunicationapp.repositories.base.ChatSessionRepository;
import source.kevtimov.landlordcommunicationapp.services.base.ChatSessionService;

public class HttpChatSessionService implements ChatSessionService {


    private ChatSessionRepository chatRepository;

    public HttpChatSessionService(ChatSessionRepository repository) {
        this.chatRepository = repository;
    }


    @Override
    public ChatSession createChat(ChatSession chat) throws IOException {
        return chatRepository.createChat(chat);
    }

    @Override
    public List<ChatSession> checkIfChatSessionExistsByTenantIdAndLandlordId(int tenantId, int landlordId) throws IOException {
        return chatRepository.checkIfChatSessionExistsByTenantIdAndLandlordId(tenantId, landlordId);
    }

    @Override
    public List<ChatSession> getAllSessionsByTenantId(int tenantId) throws IOException {
        return chatRepository.getAllSessionsByTenantId(tenantId);
    }

    @Override
    public List<ChatSession> getAllSessionsByLandlordId(int landlordId) throws IOException {
        return chatRepository.getAllSessionsByLandlordId(landlordId);
    }
}
