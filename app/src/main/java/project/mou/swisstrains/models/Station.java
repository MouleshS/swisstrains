package project.mou.swisstrains.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mou on 2/5/2018.
 */

public class Station {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
