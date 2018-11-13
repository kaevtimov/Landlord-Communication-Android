package source.kevtimov.landlordcommunicationapp.chat.templatemessage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class TemplateMessagePresenter implements ContractsTemplateMessage.Presenter {

    private ContractsTemplateMessage.View mView;
    private List<String> mTemplateMessages;

    @Inject
    public TemplateMessagePresenter(){
        this.mTemplateMessages = new ArrayList<>();
    }

    @Override
    public void subscribe(ContractsTemplateMessage.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }

    @Override
    public void allowNavigationToChat(String message) {
        mView.navigateToChat(message);
    }

    @Override
    public void loadTemplateMessages() {
        mTemplateMessages.clear();
        mTemplateMessages.add(Constants.RUNNING_A_FEW_MINUTES_LATE);
        mTemplateMessages.add(Constants.SOUNDS_GOOD_TEMPL_MESSAGE);
        mTemplateMessages.add(Constants.WONT_BE_ABLE_TO_MAKE_IT);
        mTemplateMessages.add(Constants.I_WILL_BE_THERE_IN_5_MINS);
        mTemplateMessages.add(Constants.IN_MEETING_AT_THE_MOMENT);
        mTemplateMessages.add(Constants.PLEASE_CALL_ME);
        mTemplateMessages.add(Constants.THANK_YOU_FOR_EVERYTHING);
        mTemplateMessages.add(Constants.MEET_ME_IN_THE_APARTMENT);
        mTemplateMessages.add(Constants.DIFFICULT_TO_TALK);
        mTemplateMessages.add(Constants.THAT_SOUNDS_AWESOME);

        for (String message:mTemplateMessages) {
            mView.showMessages(message);
        }
    }
}
