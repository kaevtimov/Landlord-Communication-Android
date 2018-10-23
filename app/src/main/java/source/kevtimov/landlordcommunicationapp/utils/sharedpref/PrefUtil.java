package source.kevtimov.landlordcommunicationapp.utils.sharedpref;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
