package project.mou.swisstrains.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mou on 2/15/2018.
 */

public class MySQLiteInstance {

    private static MySQLiteHelper mydb = null;

    public static void open(Context context) {

        if (mydb != null) return;
        mydb = new MySQLiteHelper(context);
    }

    public static SQLiteDatabase getDb() {
        try {
            return mydb.getWritableDatabase();
        } catch (Exception e) {
            return null;
        }
    }

    public static void close() {
        //   mydb.close();
        // mydb = null;
    }
}
