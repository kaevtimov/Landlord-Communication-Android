package source.kevtimov.landlordcommunicationapp.services.implementation;

import java.util.List;

import source.kevtimov.landlordcommunicationapp.repositories.base.TemplateMessageRepository;
import source.kevtimov.landlordcommunicationapp.services.base.TemplateMessageService;

public class SQLiteTemplateMessageService implements TemplateMessageService {

    private TemplateMessageRepository mTemplateMessageRepository;

    public SQLiteTemplateMessageService(TemplateMessageRepository repository){
        this.mTemplateMessageRepository = repository;
    }

    @Override
    public List<String> getAllTemplateMessages() {
        return mTemplateMessageRepository.getAllTemplateMessages();
    }

    @Override
    public void addTemplateMessage(String message) {
        mTemplateMessageRepository.addTemplateMessage(message);
    }
}
