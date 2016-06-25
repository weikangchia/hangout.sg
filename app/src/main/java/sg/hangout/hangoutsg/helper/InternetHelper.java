package sg.hangout.hangoutsg.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by weikang on 25/6/16.
 */
public class InternetHelper {
    private static final String TAG = "InternetHelper";

    public static boolean hasActiveInternetConnection(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null) {
            HttpURLConnection urlc = null;
            try {
                urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == HttpURLConnection.HTTP_OK);
            } catch (IOException e) {
                Log.e(TAG, "Error checking internet connection");
            } finally {
                urlc.disconnect();
            }
        } else {
            Log.e(TAG, "No network available!");
        }
        return false;
    }
}