package project.mou.swisstrains.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import project.mou.swisstrains.R;
import project.mou.swisstrains.Utils.Prefs;
import project.mou.swisstrains.Utils.Util;
import project.mou.swisstrains.database.TConnection;
import project.mou.swisstrains.models.MProfile;

import static project.mou.swisstrains.Constants.CUR_PROFILE;

/**
 * Created by Mou on 2/5/2018.
 */

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private ImageView userPic;


    public static void navigate(Context context) {
        Intent intent = new Intent(context, HomeScreenActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        userName = findViewById(R.id.user_name);
        userPic = findViewById(R.id.user_img);

        findViewById(R.id.search_con_label).setOnClickListener(this);
        findViewById(R.id.fav_con_label).setOnClickListener(this);
        fillUserInfo();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_con_label:
                SearchConnectionActivity.navigate(HomeScreenActivity.this);
                break;

            case R.id.fav_con_label:
                if (TConnection.getSavedCount() > 0) {
                    SavedConnectionActivity.navigate(HomeScreenActivity.this);
                } else {
                    Util.showToast(HomeScreenActivity.this, HomeScreenActivity.this.getResources().getString(R.string.no_saved_data));
                }
                break;
        }
    }

    private void fillUserInfo() {
        MProfile profile = null;
        String json = Prefs.getString(CUR_PROFILE);
        if (json != null) profile = new Gson().fromJson(json, MProfile.class);
        if (profile != null) {
            Util.displayPic(HomeScreenActivity.this, userPic, profile.getPicUrl(), R.drawable.place_holder);
            userName.setText(profile.getName());
        }
    }
}
