package source.kevtimov.landlordcommunicationapp.chat.fcm;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.net.MediaType;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.apache.http.client.methods.HttpPost;

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
import okhttp3.ResponseBody;
import source.kevtimov.landlordcommunicationapp.chat.chatRooms.ChatRoomActivity;
import source.kevtimov.landlordcommunicationapp.http.OkHttpHttpRequester;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;
import source.kevtimov.landlordcommunicationapp.views.login.LoginActivity;

public class ChatMessagingService extends FirebaseMessagingService {
    public static final String SERVER_URL = Constants.BASE_SERVER_URL_KRIS + "/users";
    private User mUser;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        sendTokenToServer();
    }

    private void sendTokenToServer() {
        OkHttpClient client = new OkHttpClient();
        final String[] newToken = new String[1];
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            newToken[0] = instanceIdResult.getToken();
            Log.e("newtoken",instanceIdResult.getToken());
        });
        RequestBody body = null;
        if (mUser !=null) {
            String url = SERVER_URL + "/" + mUser.getUsername() + "registrationtoken";
            body = RequestBody.create(okhttp3.MediaType.parse("application/json"), newToken[0]);
        }
        Request request = null;
        if (body != null) {
            request = new Request.Builder()
                    .url(SERVER_URL + "/" + mUser.getUsername() + "/registrationtoken")
                    .post(body)
                    .build();
        }

        if (request != null) {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try(ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful()) {
                            throw new IOException("Something went wrong " + responseBody.string());
                        }
                    }
                }
            });
        }
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

        Intent intent = new Intent(this,ChatRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"fcm-instance-specific")
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
