package source.kevtimov.landlordcommunicationapp.chat.fcm;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class ChatMessagingService extends FirebaseMessagingService {
    public static final String SERVER_URL = Constants.BASE_SERVER_URL_KRIS + "/users";

    @Override
    public void onNewToken(String s) {
        final String[] newToken = new String[1];
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener((Executor) ChatMessagingService.this,
                instanceIdResult -> newToken[0] = instanceIdResult.getToken());
        sendTokenToServer(newToken);
    }

    private void sendTokenToServer(String[] newToken) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",newToken[0])
                .build();
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(ChatMessagingService.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()){
                    throw new IOException("Sorry something went wrong");
                }
            }
        });
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            sendNotification(null,remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification(), null);
        }
    }

    private void sendNotification(RemoteMessage.Notification notification, Map<String, String> data) {
        String title = null;
        String body = null;

        if (notification != null) {
            title = notification.getTitle();
            body = notification.getBody();
        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"fcm-instance-specific")
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
    }


}
