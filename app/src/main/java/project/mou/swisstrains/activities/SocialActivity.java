package project.mou.swisstrains.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import project.mou.swisstrains.R;
import project.mou.swisstrains.Utils.Prefs;
import project.mou.swisstrains.Utils.Util;
import project.mou.swisstrains.models.MProfile;

import static project.mou.swisstrains.Constants.CUR_PROFILE;
import static project.mou.swisstrains.Constants.IS_LOGGED_IN;

/**
 * Created by Mou on 2/5/2018.
 */

public class SocialActivity extends AppCompatActivity {
    private static final int FB_IMG_WH = 250;
    private CallbackManager callbackManager;
    private TextView fbLogin;

    public static void navigate(Context context) {
        Intent intent = new Intent(context, SocialActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_layout);
        fbLogin = findViewById(R.id.fb_login_btn);
        initFb();
        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(SocialActivity.this, Arrays.asList("public_profile", "email"));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void initFb() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Success", "Login");
                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    Profile profile = Profile.getCurrentProfile();
                                    Uri picUrl = profile.getProfilePictureUri(FB_IMG_WH, FB_IMG_WH);
                                    saveNProceed(name, email, picUrl);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Util.showToast(SocialActivity.this, SocialActivity.this.getResources().getString(R.string.login_cancel));
            }

            @Override
            public void onError(FacebookException exception) {
                Util.showToast(SocialActivity.this, SocialActivity.this.getResources().getString(R.string.something_wrong));
            }
        });
    }

    private void saveNProceed(String name, String email, Uri picUri) {
        MProfile mProfile = new MProfile();
        mProfile.setName(name);
        mProfile.setEmail(email);
        if (picUri != null) mProfile.setPicUrl(picUri.toString());
        Prefs.put(IS_LOGGED_IN, true);
        Prefs.put(CUR_PROFILE, new Gson().toJson(mProfile));
        HomeScreenActivity.navigate(SocialActivity.this);
        Util.showToast(SocialActivity.this, SocialActivity.this.getResources().getString(R.string.login_success));
        finish();
    }

}

