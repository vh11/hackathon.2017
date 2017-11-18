package org.hackathon.common.model;

import org.hackathon.common.store.PatientStore;
import org.hackathon.common.store.RegionStore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by vh on 11/18/17.
 */

public class Diagnostic extends Entity {

    private String title;
    private int regionId;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void deserialize(String serialized) {
        String[] parts = serialized.split(",");
        setId(Integer.parseInt(parts[0].trim()));
        setTitle(parts[1].trim());
        setRegionId(Integer.parseInt(parts[2].trim()));
    }

    public Region getRegion() {
        return RegionStore.getInstance().get(getRegionId());
    }
}
