package source.kevtimov.landlordcommunicationapp.chat.chatRooms;

import android.annotation.SuppressLint;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
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
    public void sendMessage(Message message, User to) throws IOException {
        message.setReceiverToken(to.getToken());

        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Message>) emitter ->{
            Message newMessage = mService.sendMessage(message);
            emitter.onNext(newMessage);
            emitter.onComplete();
        })
                .observeOn(mSchedulerProvider.background())
                .subscribeOn(mSchedulerProvider.ui())
                .subscribe(this::showMessage);

        //mService.sendMessage(message);
        showMessage(message);
    }

    @Override
    public void parseReceivedMessage() {
        Disposable observable = Observable.create((ObservableOnSubscribe<Message>) emitter -> {
            Message object = mService.receiveMessage();
            emitter.onNext(object);
            emitter.onComplete();
        })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(this::showReceivedMessage);
    }

    @Override
    public void loadConversation(String senderToken, String receiverToken) {

    }

    private void showMessage(Message message) {
        mView.displayMyMessage(message);
    }

    private void showReceivedMessage(Message message) {
        String body = message.getMessageBody();
        mView.displayReceivedMessage(body);
    }
}
