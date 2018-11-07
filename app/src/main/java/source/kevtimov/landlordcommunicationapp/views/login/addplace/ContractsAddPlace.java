package source.kevtimov.landlordcommunicationapp.views.login.addplace;

import android.os.Bundle;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsAddPlace {

    interface View {

        void setPresenter(Presenter presenter);

        void setNavigator(Navigator navigator);

        void showLoading();

        void hideLoading();

        void showError(Throwable error);

        void navigateToPlaceManagementOnCancel();

        void navigateToPlaceManagementOnSave(Bundle info);

        void navigateToSelectTenant();

        void setUserTenant(User tenant);

        void alertDialogManagement();

        void alertForAddressConstraint();

        void alertForDescriptionConstraint();

        void alertForTotalAmountConstraint();

        void alertForYearConstraint();

        void alertForMonthConstraint();

        void alertForDayConstraint();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void allowNavigationOnCancel();

        void allowNavigationOnSave(Bundle info);

        void allowNavigateToSelectTenant();

        void checkInputInfo(String address, String description, String total, String year, String month, String day);
    }

    interface Navigator {

        void navigateToPlaceManagementActivity(Bundle info);

        void navigateOnCancel();

        void navigateToSelectTenant();
    }
}
