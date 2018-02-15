package project.mou.swisstrains.viewholders;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import project.mou.swisstrains.R;
import project.mou.swisstrains.Utils.Util;
import project.mou.swisstrains.database.TConnection;
import project.mou.swisstrains.models.Route;

import static project.mou.swisstrains.Constants.formatDTTIME;
import static project.mou.swisstrains.adapters.RoutesAdapter.CON_MODE_LOCAL;

/**
 * Created by Mou on 2/15/2018.
 */

public class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView from, to, fromPf, toPf, frmArTs, frmDepTs, toArTs, toDepTs, duration, save;
    private Route curRoute;
    private Context ctx;
    private int mode;

    public RouteViewHolder(Context context, View itemView, int mode) {
        super(itemView);
        this.ctx = context;
        this.mode = mode;

        duration = itemView.findViewById(R.id.duration);
        from = itemView.findViewById(R.id.from);
        to = itemView.findViewById(R.id.to);
        fromPf = itemView.findViewById(R.id.from_platform);
        toPf = itemView.findViewById(R.id.to_platform);
        //frmArTs = itemView.findViewById(R.id.fromArTs);
        frmDepTs = itemView.findViewById(R.id.fromDepTs);
        toArTs = itemView.findViewById(R.id.toArTs);
        //toDepTs = itemView.findViewById(R.id.toDepTs);
        save = itemView.findViewById(R.id.save);
        if (mode == CON_MODE_LOCAL) {
            save.setVisibility(View.GONE);
        } else {
            save.setVisibility(View.VISIBLE);
            save.setOnClickListener(this);
        }

    }

    public static RouteViewHolder get(Context context, ViewGroup parent, int mode) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_row_layout, parent, false);
        return new RouteViewHolder(context, v, mode);
    }

    public void bind(Route route) {
        if (route != null) {
            curRoute = route;
            from.setText(route.getFrom().getStation().getName());
            to.setText(route.getTo().getStation().getName());
            duration.setText("duration: " + route.getDuration());
            fromPf.setText("Platform: " + route.getFrom().getPlatform());
            toPf.setText("Platform: " + route.getTo().getPlatform());
            frmDepTs.setText("Dep: " + Util.getFormattedDate(route.getFrom().getDepatureTime(), formatDTTIME));
            toArTs.setText("Arv: " + Util.getFormattedDate(route.getTo().getArrivalTime(), formatDTTIME));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == save.getId()) {
            if (curRoute != null) {
                String json = new Gson().toJson(curRoute);
                String id = Util.generateMd5(json);
                long val = TConnection.insert(id,json);
                if (val != -1)
                    Util.showToast((Activity) ctx, ctx.getResources().getString(R.string.con_saved));
                else
                    Util.showToast((Activity) ctx, ctx.getResources().getString(R.string.con_exists));
            }
        }
    }
}
