package project.mou.swisstrains.Utils;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by Mou on 3/22/2017.
 * utility to get global application context
 */

public class Contextor {

    @SuppressLint("StaticFieldLeak")
    private static Contextor sInstance;

    private Context mContext;

    public static Contextor getInstance() {
        if (sInstance == null) {
            sInstance = new Contextor();
        }
        return sInstance;
    }

    public void init(Context pContext) {
        mContext = pContext;
    }

    public Context getContext() {
        return mContext;
    }
}
