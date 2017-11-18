package org.hackathon.common.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by vh on 11/18/17.
 */
public class Region extends Entity {

    private String subRegion;
    private String title;

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void deserialize(String serialized) {
        String[] parts = serialized.split(",");
        setId(Integer.parseInt(parts[0].trim()));
        setSubRegion(parts[1].trim());
        setTitle(parts[2].trim());
    }}
