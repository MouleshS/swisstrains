package project.mou.swisstrains.application;

import android.app.Application;

import project.mou.swisstrains.Utils.Contextor;
import project.mou.swisstrains.Utils.Prefs;
import project.mou.swisstrains.database.MySQLiteInstance;

/**
 * Created by Mou on 2/15/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(this); // init context getter utility
        Prefs.init(this, Prefs.APPLY); // init shared preferences utility
        MySQLiteInstance.open(this);
    }
}
