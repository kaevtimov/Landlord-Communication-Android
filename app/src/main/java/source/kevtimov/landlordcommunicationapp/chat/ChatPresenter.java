package source.kevtimov.landlordcommunicationapp.chat;


import java.util.List;
import java.util.Observable;

import javax.inject.Inject;


import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.UserService;

public class ChatPresenter implements ChatContracts.Presenter {
    private ChatContracts.View mView;
    private SchedulerProvider mSchedulerProvider;
    private UserService mService;

    @Inject
    public ChatPresenter(SchedulerProvider schedulerProvider, UserService service) {
        mSchedulerProvider = schedulerProvider;
        mService = service;
    }

    @Override
    public void selectLandlord(User landlord) {
        mView.openChatRoom(landlord);
    }

    @Override
    public void subscribe(ChatContracts.View view) {
        mView = view;
    }

    @Override
    public void loadLandlords() {
        Disposable disposable = io.reactivex.Observable
                .create((ObservableOnSubscribe<List<User>>) emitter -> {
                    List<User> landlords = mService.getLandlords();
                    emitter.onNext(landlords);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(this::presentLandlordsToView,
                        error->mView.showError(error));
    }

    private void presentLandlordsToView(List<User> landlordList) {
        if (landlordList.isEmpty()) {
            mView.showEmptyLandlordList();
        } else {
            mView.showLandlords(landlordList);
        }
    }
}
