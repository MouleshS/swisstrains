package project.mou.swisstrains.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import project.mou.swisstrains.R;
import project.mou.swisstrains.Utils.Util;
import project.mou.swisstrains.adapters.RoutesAdapter;
import project.mou.swisstrains.apiservice.ApiAdapter;
import project.mou.swisstrains.apiservice.ApiService;
import project.mou.swisstrains.models.Result;
import project.mou.swisstrains.models.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mou on 2/5/2018.
 */

public class SearchConnectionActivity extends AppCompatActivity {

    private EditText fromEdit, toEdit;
    private TextView search;
    private RecyclerView rv;

    public static void navigate(Context context) {
        Intent intent = new Intent(context, SearchConnectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.search_con);
        }
        fromEdit = findViewById(R.id.from_edit);
        toEdit = findViewById(R.id.to_edit);
        search = findViewById(R.id.search);
        rv = findViewById(R.id.rv);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.checkEditInput(SearchConnectionActivity.this, fromEdit) && Util.checkEditInput(SearchConnectionActivity.this, toEdit)) {
                    //getConnections("Lausanne", "Genève");
                    getConnections(fromEdit.getText().toString(), toEdit.getText().toString());
                }
            }
        });
    }

    private void getConnections(String from, String to) {
        ApiService apiService = ApiAdapter.getApiService();
        Call<Result> call = apiService.getConnections(from, to);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() != null && response.body().getConnections() != null) {
                    populateRv(response.body().getConnections());
                } else {
                    Util.showToast(SearchConnectionActivity.this, SearchConnectionActivity.this.getResources().getString(R.string.no_con_found));
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Util.showToast(SearchConnectionActivity.this, SearchConnectionActivity.this.getResources().getString(R.string.something_wrong));
            }
        });
    }

    private void populateRv(List<Route> routes) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchConnectionActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        RoutesAdapter adapter = new RoutesAdapter(SearchConnectionActivity.this, routes, RoutesAdapter.CON_MODE_API);
        rv.setAdapter(adapter);
    }
}
