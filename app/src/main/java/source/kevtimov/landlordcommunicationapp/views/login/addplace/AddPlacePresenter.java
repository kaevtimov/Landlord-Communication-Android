package source.kevtimov.landlordcommunicationapp.views.login.addplace;

import android.os.Bundle;

import javax.inject.Inject;

import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.services.base.PlaceService;
import source.kevtimov.landlordcommunicationapp.services.base.RentService;

public class AddPlacePresenter implements ContractsAddPlace.Presenter {

    private ContractsAddPlace.View mView;
    private PlaceService mPlaceSeevice;
    private RentService mRentService;
    private SchedulerProvider mSchedulerProvider;
    private User mUserTenant;

    @Inject
    public AddPlacePresenter(SchedulerProvider schedulerProvider, PlaceService placeService, RentService rentService){
        this.mSchedulerProvider = schedulerProvider;
        this.mPlaceSeevice = placeService;
        this.mRentService = rentService;
    }

    @Override
    public void subscribe(ContractsAddPlace.View view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void allowNavigationOnCancel() {
        mView.navigateToPlaceManagementOnCancel();
    }

    @Override
    public void allowNavigationOnSave(Bundle info) {
        mView.navigateToPlaceManagementOnSave(info);
    }

    @Override
    public void setUserTenant(User tenant) {
        this.mUserTenant = tenant;
    }

    @Override
    public void allowNavigateToSelectTenant() {
        mView.navigateToSelectTenant();
    }

    @Override
    public void checkInputInfo(String address, String description, String total, String year, String month, String day) {

        Bundle info = new Bundle();

        if(address.length() < 5){
            mView.alertForAddressConstraint();
        } else if(description.length() < 10){
            mView.alertForDescriptionConstraint();
        } else if(total.length() < 3
                || Double.parseDouble(total) < 100){
            mView.alertForTotalAmountConstraint();
        } else if(year.length() < 4
                || Integer.parseInt(year) < 2018 || Integer.parseInt(year) > 2050){
            mView.alertForYearConstraint();
        } else if(month.length() < 1
                || Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12){
            mView.alertForMonthConstraint();
        } else if(day.length() < 1
                || Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31){
            mView.alertForDayConstraint();
        } else{
            if (month.length() == 1 && day.length() == 1) {
                info.putString("due_date", year + "-0"
                        + month + "-0" + day);
            } else if (day.length() == 1) {
                info.putString("due_date", year + "-"
                        + month + "-0" + day);
            } else if (month.length() == 1){
                info.putString("due_date", year + "-0"
                        + month + "-" + day);
            } else{
                info.putString("due_date", year + "-"
                        + month + "-" + day);
            }
            info.putSerializable("tenant", mUserTenant);
            info.putString("address", address);
            info.putString("description", description);
            info.putString("total_amount", total);
            mView.alertDialogManagement(info);
        }
    }
}
