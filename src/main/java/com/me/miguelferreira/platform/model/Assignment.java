package com.me.miguelferreira.platform.model;

public class Assignment extends BaseEntity {

    private GeographicalLocation geographicalLocation;
    private Address address;
    private Requester requester;

    public GeographicalLocation getGeographicalLocation() {
        return geographicalLocation;
    }

    public void setGeographicalLocation(final GeographicalLocation geographicalLocation) {
        this.geographicalLocation = geographicalLocation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(final Requester requester) {
        this.requester = requester;
    }
    
}
