package source.kevtimov.landlordcommunicationapp.views.login.selecttenant;


import java.util.List;

import source.kevtimov.landlordcommunicationapp.models.User;

public interface ContractsSelectTenant {

    interface View {

        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void setNavigator(Navigator navigator);

        void navigateOnCancel();

        void navigateOnDone(User tenant);

        void showError(Throwable error);

        void showTenants(List<User> tenants);

        void showEmptyList();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void loadTenants();

        void allowNavigateOnCancel();

        void allowNavigateOnDone(User tenant);
    }

    interface Navigator{
        void navigateToAddPlaceOnCancel();

        void navigateToAddPlaceOnDone(User tenant);
    }
}
