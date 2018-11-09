package source.kevtimov.landlordcommunicationapp.views.login.payment;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.Card;
import source.kevtimov.landlordcommunicationapp.models.Payment;
import source.kevtimov.landlordcommunicationapp.models.Place;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.CardService;
import source.kevtimov.landlordcommunicationapp.services.base.PaymentService;
import source.kevtimov.landlordcommunicationapp.services.base.RentService;

public class PaymentsPresenter implements ContractsPayments.Presenter {

    private ContractsPayments.View mView;
    private SchedulerProvider mSchedulerProvider;
    private CardService mCardService;
    private PaymentService mPaymentService;
    private RentService mRentService;
    private Place mPlace;
    private User mUser;
    private Rent mRent;
    private Card mCard;

    @Inject
    public PaymentsPresenter(SchedulerProvider schedulerProvider, PaymentService paymentService, RentService rentService,
                             CardService cardService) {
        this.mSchedulerProvider = schedulerProvider;
        this.mCardService = cardService;
        this.mRentService = rentService;
        this.mPaymentService = paymentService;
    }


    @Override
    public void subscribe(ContractsPayments.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        this.mView = null;
    }

    @Override
    public void setUser(User user) {
        this.mUser = user;
    }

    @Override
    public void setPlace(Place place) {
        this.mPlace = place;
    }

    @Override
    public void setRent(Rent rent) {
        this.mRent = rent;
    }

    @Override
    public void setCard(Card card) {
        this.mCard = card;
    }

    @Override
    public void loadRent() {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rent>) emitter -> {
                    Rent rent = mRentService.getRentByPlaceId(mPlace.getPlaceID());
                    emitter.onNext(rent);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(mView::manageRentView
                        , error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void loadCards() {
        mView.showLoading();
        Disposable observal = Observable
                .create((ObservableOnSubscribe<List<Card>>) emitter -> {
                    List<Card> cards = mCardService.getAllCardsByUserId(mUser.getUserId());
                    emitter.onNext(cards);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(mView::manageCardView
                        , error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    @Override
    public void managePayment(double enteredAmount) {
        // check if user has enough money in card to pay
        // create payment
        // update rent (create rent with new date OR update rent remaining amount)
        // decrease card amount

        if (mCard.getBalance() <= enteredAmount) {
            mView.alertNotEnoughMoney();
        } else if (enteredAmount > mRent.getRemainingAmount()) {
            mView.alertEnteredAmountBiggerThanRemaining();
        } else {
            if (enteredAmount == mRent.getRemainingAmount()) {
                updateLastRentAsPaid();
                createNewRent();
            } else {
                updateRentRemainingAmount(enteredAmount);
            }
            createPayment(enteredAmount);
            updateCardBalance(enteredAmount);
        }
        mView.navigateToDetailsActivity();
    }

    @Override
    public void allowNavigateToCardAdd() {
        mView.navigateToCardAdd();
    }

    private void updateCardBalance(double enteredAmount) {

        double newBalance = mCard.getBalance() - enteredAmount;
        Card outcomeCard = new Card("", "", "", "", newBalance, mUser.getUserId());

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Card>) emitter -> {
                    Card card = mCardService.updateCardBalance(mCard.getCardID(), outcomeCard);
                    emitter.onNext(card);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(r -> {
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    private void createPayment(double enteredAmount) {

        Date current = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String paymentDate = format.format(current);
        Payment payment = new Payment(enteredAmount, paymentDate, mUser.getUserId(), mCard.getCardID(), mRent.getRentID(), mPlace.getPlaceID());

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Payment>) emitter -> {
                    Payment paymentResponse = mPaymentService.createPayment(payment);
                    emitter.onNext(paymentResponse);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(r -> {
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    private void updateRentRemainingAmount(double enteredAmount) {

        double remainingAmount = mRent.getRemainingAmount() - enteredAmount;
        Rent outcomeRent = new Rent(0, 0, remainingAmount, "", false);

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rent>) emitter -> {
                    Rent rent = mRentService.updateRentRemainingAmount(mRent.getRentID(), outcomeRent);
                    emitter.onNext(rent);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(r -> {
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }


    private void updateLastRentAsPaid() {

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rent>) emitter -> {
                    Rent rent = mRentService.updatePaidStatus(mRent.getRentID());
                    emitter.onNext(rent);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(r -> {
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }

    private void createNewRent() {
        Rent newRent = new Rent(mPlace.getPlaceID(), mRent.getTotalAmount(), mRent.getTotalAmount(), mRent.getDueDate(), false);

        Disposable observal = Observable
                .create((ObservableOnSubscribe<Rent>) emitter -> {
                    Rent rent = mRentService.registerNextRent(newRent);
                    emitter.onNext(rent);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doFinally(mView::hideLoading)
                .subscribe(r -> {
                        },
                        error -> {
                            if (error instanceof NullPointerException) {
                                // in case of null pointer exception
                            } else {
                                mView.showError(error);
                            }
                        });
    }
}
