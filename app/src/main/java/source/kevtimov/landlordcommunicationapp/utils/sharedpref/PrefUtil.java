package source.kevtimov.landlordcommunicationapp.utils.sharedpref;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class PrefUtil {

    private Activity activity;

    public PrefUtil(Activity activity) {
        this.activity = activity;
    }

    public void saveAccessToken(String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_access_token", token);
        editor.apply();
    }

    public void saveFCMToken(String token, String username) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fcm_token",token);
        Log.i("token",token);
        editor.apply();
        sendTokenToDB(token,username);
    }

    private void sendTokenToDB(String token, String username) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = null;
        body = new FormBody.Builder()
                .add("token",token)
                .build();
        Request request = null;
        request = new Request.Builder()
                .url(Constants.BASE_SERVER_URL_KRIS + "/users/" + username)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Something went wrong " + responseBody.string());
                    }
                }
            }
        });

    }

    public String getFCMToken() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getString("fcm_token",null);
    }

    public String getToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("fb_access_token", null);
    }

    public void clearToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void saveFacebookUserInfo(String firstName,String lastName, String email, String profileURL){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_first_name", firstName);
        editor.putString("fb_last_name", lastName);
        editor.putString("fb_email", email);
        editor.putString("fb_profileURL", profileURL);
        editor.apply();
    }
}
