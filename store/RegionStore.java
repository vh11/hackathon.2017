package org.hackathon.common.store;

import org.hackathon.common.model.Prescription;
import org.hackathon.common.model.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vh on 11/18/17.
 */
public class RegionStore extends Store<Region> {

    private static RegionStore instance;

    private RegionStore() {
    }

    public static synchronized RegionStore getInstance() {
        if (instance == null) {
            instance = new RegionStore();
        }

        return instance;
    }

    @Override
    Region newItem() {
        return new Region();
    }
}
