package project.mou.swisstrains.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mou on 2/15/2018.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "trains.db";
    private static final int DATABASE_VERSION = 1;
    public static String TABLE_CONNECTION = "con_table";
    public static String V_ID = "_id";
    public static String V_CONNECTION = "connection";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TConnection.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TConnection.onUpgrade(db);
    }
}
