package source.kevtimov.landlordcommunicationapp.chat.chatview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Message;
import source.kevtimov.landlordcommunicationapp.services.base.MessageService;

public class ChatPresenter implements ContractsChat.Presenter {

    private ContractsChat.View mView;
    private SchedulerProvider mSchedulerProvider;
    private MessageService mMessageService;
    private int mLoggedInUser;
    private int mOtherUser;
    private int mCurrentSession;


    @Inject
    public ChatPresenter(SchedulerProvider schedulerProvider, MessageService messageService) {
        this.mSchedulerProvider = schedulerProvider;
        this.mMessageService = messageService;
    }

    @Override
    public void subscribe(ContractsChat.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setLoggedInUser(int userId) {
        this.mLoggedInUser = userId;
    }

    @Override
    public void setOtherUser(int userId) {
        this.mOtherUser = userId;
    }

    @Override
    public void setSession(int chatSessionId) {
        this.mCurrentSession = chatSessionId;
    }

    @Override
    public void getAllMessagesByChatId() {

        Timer mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Disposable observal = Observable
                        .create((ObservableOnSubscribe<List<Message>>) emitter -> {
                            List<Message> messages = mMessageService.getMessagesByChatId(mCurrentSession);
                            emitter.onNext(messages);
                            emitter.onComplete();
                        })
                        .subscribeOn(mSchedulerProvider.background())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(this::viewMessages,
                                error -> mView.showError(error));

            }

            public void viewMessages(List<Message> messages) {

                if (mView == null) {
                    mTimer.cancel();
                } else {
                    if (!messages.isEmpty()) {
                        mView.showMessages(messages);
                    }
                }
            }
        };
        mTimer.scheduleAtFixedRate(mTimerTask, 0, 3000);
    }


    @Override
    public void createMessage(String msgContent) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        Message message = new Message(strDate, msgContent, mLoggedInUser, mOtherUser, mCurrentSession);

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Message>) emitter -> {
                    Message sendingMessage = mMessageService.createMessage(message);
                    emitter.onNext(sendingMessage);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(r -> {},
                        error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void allowNavigationToTemplateMessages() {
        mView.navigateToMessageTemplates();
    }
}
