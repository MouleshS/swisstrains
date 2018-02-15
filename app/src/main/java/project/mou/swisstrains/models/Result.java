package project.mou.swisstrains.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mou on 2/5/2018.
 */

public class Result {
    @SerializedName("from")
    private Station from;
    @SerializedName("to")
    private Station to;
    @SerializedName("connections")
    private List<Route> connections;

    public List<Route> getConnections() {
        return connections;
    }

    public void setConnections(List<Route> connections) {
        this.connections = connections;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station to) {
        this.to = to;
    }
}
