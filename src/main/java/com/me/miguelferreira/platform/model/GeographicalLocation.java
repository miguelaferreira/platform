package com.me.miguelferreira.platform.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GeographicalLocation {

    private double latitude;
    private double longitude;

    public GeographicalLocation(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeographicalLocation() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
