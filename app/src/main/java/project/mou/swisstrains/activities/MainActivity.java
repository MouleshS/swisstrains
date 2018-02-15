package project.mou.swisstrains.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.mou.swisstrains.R;
import project.mou.swisstrains.Utils.Prefs;
import project.mou.swisstrains.apiservice.ApiAdapter;
import project.mou.swisstrains.apiservice.ApiService;
import project.mou.swisstrains.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static project.mou.swisstrains.Constants.IS_LOGGED_IN;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigate();

        //getConnections("Lausanne", "Genève");
    }


    private void navigate() {
        if (Prefs.getBoolean(IS_LOGGED_IN)) HomeScreenActivity.navigate(MainActivity.this);
        else SocialActivity.navigate(this);
        finish();
    }


}