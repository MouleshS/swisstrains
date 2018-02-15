package project.mou.swisstrains.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import project.mou.swisstrains.R;
import project.mou.swisstrains.adapters.RoutesAdapter;
import project.mou.swisstrains.database.TConnection;
import project.mou.swisstrains.models.Route;

/**
 * Created by Mou on 2/15/2018.
 */

public class SavedConnectionActivity extends AppCompatActivity {
    private RecyclerView rv;

    public static void navigate(Context context) {
        Intent intent = new Intent(context, SavedConnectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_con_layout);
        rv = findViewById(R.id.rv);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.fav_con);
        }
        MyAsync async = new MyAsync();
        async.execute();
    }

    private void populateRv(List<Route> routes) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SavedConnectionActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        RoutesAdapter adapter = new RoutesAdapter(SavedConnectionActivity.this, routes, RoutesAdapter.CON_MODE_LOCAL);
        rv.setAdapter(adapter);
    }

    private class MyAsync extends AsyncTask<String, String, String> {
        List<Route> routeList;

        @Override
        protected String doInBackground(String... strings) {
            routeList = TConnection.getSavedCons();
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            populateRv(routeList);
        }
    }
}
