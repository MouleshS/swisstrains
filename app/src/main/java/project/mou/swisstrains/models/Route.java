package project.mou.swisstrains.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mou on 2/5/2018.
 */

public class Route {

    @SerializedName("from")
    private Destination from;
    @SerializedName("to")
    private Destination to;
    @SerializedName("duration")
    private String duration;
    private boolean isSaved;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public Destination getFrom() {
        return from;
    }

    public void setFrom(Destination from) {
        this.from = from;
    }

    public Destination getTo() {
        return to;
    }

    public void setTo(Destination to) {
        this.to = to;
    }
}
