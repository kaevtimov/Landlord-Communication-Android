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
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void allowNavigationOnCancel();

        void allowNavigationOnSave(Bundle info);

        void allowNavigateToSelectTenant();
    }

    interface Navigator {

        void navigateToPlaceManagementActivity(Bundle info);

        void navigateOnCancel();

        void navigateToSelectTenant();
    }
}
