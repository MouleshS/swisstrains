package project.mou.swisstrains.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import project.mou.swisstrains.models.Route;
import project.mou.swisstrains.viewholders.RouteViewHolder;

/**
 * Created by Mou on 2/15/2018.
 */

public class RoutesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int CON_MODE_API = 1;
    public static final int CON_MODE_LOCAL = 2;
    private Context context;
    private List<Route> routeList;
    private int mode;

    public RoutesAdapter(Context context, List<Route> routeList, int mode) {
        this.context = context;
        this.routeList = routeList;
        this.mode = mode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RouteViewHolder.get(context, parent,mode);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Route route = routeList.get(position);
        ((RouteViewHolder) holder).bind(route);
    }

    @Override
    public int getItemCount() {
        return routeList != null ? routeList.size() : 0;
    }
}
