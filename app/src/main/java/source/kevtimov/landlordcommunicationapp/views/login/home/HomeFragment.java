package source.kevtimov.landlordcommunicationapp.views.login.home;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Rent;

public class HomeFragment extends Fragment implements ContractsHome.View {


    private ContractsHome.Presenter mPresenter;
    private ContractsHome.Navigator mNavigator;

    @Inject
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.manageNotifications();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void sendNotification(int year, int month, int day, Rent rent) {

        Calendar calendar = Calendar.getInstance();

        //calendar.set(2018, 11, 5, 18, 6);
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);


        if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
            Intent intent = new Intent(getContext(), ReceiverNotification.class);
            intent.putExtra("TotalAmount", rent.getTotalAmount());
            intent.putExtra("RemainingAmount", rent.getRemainingAmount());
            intent.putExtra("DueDate", rent.getDueDate());
            intent.putExtra("RentId", rent.getRentID());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), rent.getRentID(), intent, 0);

            AlarmManager alarmManager = (AlarmManager) Objects.requireNonNull(getContext()).getSystemService(Context.ALARM_SERVICE);

            Objects.requireNonNull(alarmManager).setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }


    @Override
    public void setPresenter(ContractsHome.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setNavigator(ContractsHome.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showError(Throwable error) {

    }
}
