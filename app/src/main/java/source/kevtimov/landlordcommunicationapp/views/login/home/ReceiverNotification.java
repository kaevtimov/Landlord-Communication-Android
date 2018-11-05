package source.kevtimov.landlordcommunicationapp.views.login.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Objects;

import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.Rent;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.myplaces.MyPlacesActivity;

public class ReceiverNotification extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        Intent intentToMyPlaces = new Intent(context, MyPlacesActivity.class);
        intentToMyPlaces.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        String total = String.valueOf(intent.getDoubleExtra("TotalAmount", 0));
        String remain = String.valueOf(intent.getDoubleExtra("RemainingAmount", 0));
        int rentId = intent.getIntExtra("RentId", 0);
        String date = intent.getStringExtra("DueDate");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 595, intentToMyPlaces, 0);

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context, Constants.CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.rent_day)
                .setContentTitle("RENT!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You have 5 days till rent due date!\n" + "Due date: " + date + "\n" + "Total amount: " + total + "\n" +
                        "Remain: " + remain + "\n"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        Objects.requireNonNull(manager).notify(rentId, notBuilder.build());
    }
}
