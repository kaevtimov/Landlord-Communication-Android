package source.kevtimov.landlordcommunicationapp.views.login.addcard;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.CardService;

public class AddCardPresenter implements ContractsAddCard.Presenter {


    private ContractsAddCard.View mView;
    private SchedulerProvider mSchedulerProvider;
    private CardService mCardService;
    private User mUser;

    @Inject
    public AddCardPresenter(SchedulerProvider schedulerProvider, CardService cardService){
        this.mSchedulerProvider = schedulerProvider;
        this.mCardService = cardService;
    }


    @Override
    public void subscribe(ContractsAddCard.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void addCard(String cardType, String cardBrand, String cardNumber, String cvvNumber) {

        if(cardType.length() == 0 || cardBrand.length() == 0
                || cardNumber.length() == 0 || cvvNumber.length() == 0){
            mView.alertEmptyFields();
        } else if(cardNumber.length() < 16 || cvvNumber.length() < 3){
            mView.alertForConstraints();
        } else{
            Card createCard = new Card(cardBrand, cardType, cardNumber, cvvNumber, 1000.00, mUser.getUserId());

            Disposable observal = Observable
                    .create((ObservableOnSubscribe<Card>) emitter -> {
                        Card card = mCardService.registerCard(createCard);
                        emitter.onNext(card);
                        emitter.onComplete();
                    })
                    .subscribeOn(mSchedulerProvider.background())
                    .observeOn(mSchedulerProvider.ui())
                    .subscribe(c -> mView.navigateBack(),
                            error -> {
                                if (error instanceof NullPointerException) {
                                    // in case of null pointer exception
                                } else {
                                    mView.showError(error);
                                }
                            });
        }
    }
}
