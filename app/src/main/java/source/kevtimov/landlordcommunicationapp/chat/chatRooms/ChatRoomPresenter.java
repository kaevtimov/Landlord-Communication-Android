package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.annotation.SuppressLint;
import android.content.Intent;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.internal.operators.completable.CompletableFromCallable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.MessageService;

public class ChatRoomPresenter implements ChatRoomContracts.Presenter {
    private ChatRoomContracts.View mView;
    private MessageService mService;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public ChatRoomPresenter(MessageService mService, SchedulerProvider mSchedulerProvider) {
        this.mService = mService;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void subscribe(ChatRoomContracts.View view) {
        mView = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendMessage(String body, User to) throws IOException {
        Message message = new Message();
        message.setSendByMe(true);
        message.setMessageBody(body);
        message.setReceiverToken(to.getToken());


        Completable completable = new CompletableFromCallable(() -> {
            try {
                mService.sendMessage(message);
            } catch (Throwable e) {
                throw new IOException(e.getMessage());
            }
            return null;
        });
        showMessage(message);
    }

    @Override
    public void parseReceivedMessage(Intent intent) {
        
    }

    @Override
    public void loadConversation(String senderToken, String receiverToken) {

    }

    private void showMessage(Message message) {
        mView.displayMyMessage(message);
    }
}
