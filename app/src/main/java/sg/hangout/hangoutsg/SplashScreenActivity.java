package sg.hangout.hangoutsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.auth0.core.Token;
import com.auth0.core.UserProfile;
import com.auth0.lock.Lock;
import com.auth0.lock.LockActivity;

import sg.hangout.hangoutsg.helper.InternetHelper;

public class SplashScreenActivity extends AppCompatActivity {
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new preFetchData().execute();
    }

    private class preFetchData extends AsyncTask<Void, Void, Void> {
        private boolean error = false;
        private boolean login = false;
        private String errorMessage = "";

        @Override
        protected Void doInBackground(Void... voids) {
            if (InternetHelper.hasActiveInternetConnection(getApplicationContext())) {
                // get user preferred store
                sharedPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                String tokenId = sharedPrefs.getString("tokenId", "-1");

                if (!tokenId.equals("-1")) {
                    login = true;
                }
            } else {
                Resources res = getResources();

                error = true;
                errorMessage = res.getString(R.string.no_internet_error_msg);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            // before making http calls
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // after making the http calls
            super.onPostExecute(aVoid);

            if (error) {
                showErrorMessageDialog(errorMessage);
                finish();
            } else if (login) {
                Intent lockIntent = new Intent(getApplicationContext(), LockActivity.class);
                startActivity(lockIntent);

                LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
                broadcastManager.registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        UserProfile profile = intent.getParcelableExtra(Lock.AUTHENTICATION_ACTION_PROFILE_PARAMETER);
                        Token token = intent.getParcelableExtra(Lock.AUTHENTICATION_ACTION_TOKEN_PARAMETER);

                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        editor.putString("tokenId", token.getIdToken());
                        editor.putString("username", profile.getNickname());
                        editor.putString("profileImage", profile.getPictureURL());
                        editor.commit();
                    }
                }, new IntentFilter(Lock.AUTHENTICATION_ACTION));
            } else {

            }
        }
    }

    private void showErrorMessageDialog(final String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}