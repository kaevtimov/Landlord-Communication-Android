package source.kevtimov.landlordcommunicationapp.views;

public interface ContractsLogin {

    interface View {

        void setPresenter(Presenter presenter);

    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();
    }

    interface Navigator{

    }
}
