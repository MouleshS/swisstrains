package project.mou.swisstrains.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import project.mou.swisstrains.models.Route;

import static project.mou.swisstrains.database.MySQLiteHelper.TABLE_CONNECTION;
import static project.mou.swisstrains.database.MySQLiteHelper.V_CONNECTION;
import static project.mou.swisstrains.database.MySQLiteHelper.V_ID;


/**
 * Created by Mou on 2/15/2018.
 */

public class TConnection {


    // function to be inserted in SQL lite helper
    public static void onCreate(SQLiteDatabase db) {
        String tbl = "CREATE TABLE IF NOT EXISTS " + TABLE_CONNECTION +
                "( " +
                V_ID + " TEXT NOT NULL UNIQUE, " + // msg id
                V_CONNECTION + " TEXT," +
                " PRIMARY KEY( " + V_ID + ")" + ");";
        db.execSQL(tbl);
    }

    // function to be inserted in SQL lite helper
    public static void onUpgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONNECTION);
    }

    public static long insert(String id, String json) {
        long rowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(V_ID, id);
            values.put(V_CONNECTION, json);

            rowId = MySQLiteInstance.getDb().insertWithOnConflict(TABLE_CONNECTION, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        } catch (Exception e) {
        }
        return rowId;
    }

    public static List<Route> getSavedCons() {
        List<Route> routeList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + TABLE_CONNECTION;
            Cursor cursor = MySQLiteInstance.getDb().rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String json = cursor.getString(cursor.getColumnIndex(V_CONNECTION));
                    if (json != null) {
                        Route route = new Gson().fromJson(json, Route.class);
                        routeList.add(route);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
        }

        return routeList;
    }

    public static long getSavedCount() {
        long cnt = 0;
        try {
            cnt = DatabaseUtils.queryNumEntries(MySQLiteInstance.getDb(), TABLE_CONNECTION);
        } catch (Exception e) {

        }
        return cnt;
    }

}
