package source.kevtimov.landlordcommunicationapp.services.base;

import java.io.IOException;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.ChatSession;

public interface ChatSessionService {

    ChatSession createChat(ChatSession chat) throws IOException;

    List<ChatSession> checkIfChatSessionExistsByTenantIdAndLandlordId(int tenantId, int landlordId) throws IOException;

    List<ChatSession> getAllSessionsByTenantId(int tenantId) throws IOException;

    List<ChatSession> getAllSessionsByLandlordId(int landlordId) throws IOException;
}
