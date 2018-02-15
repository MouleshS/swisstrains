package project.mou.swisstrains.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mou on 2/5/2018.
 */

public class Destination {
    @SerializedName("station")
    private Station station;
    @SerializedName("platform")
    private String platform;
    @SerializedName("departureTimestamp")
    private Long depatureTime;
    @SerializedName("arrivalTimestamp")
    private Long arrivalTime;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getDepatureTime() {
        return depatureTime;
    }

    public void setDepatureTime(Long depatureTime) {
        this.depatureTime = depatureTime;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
