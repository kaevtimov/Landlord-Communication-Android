package source.kevtimov.landlordcommunicationapp.views.login.home;

public interface ContractsHome {

    interface View {

        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void setNavigator(Navigator navigator);

        void showError(Throwable error);

    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();


    }

    interface Navigator {


    }

}
