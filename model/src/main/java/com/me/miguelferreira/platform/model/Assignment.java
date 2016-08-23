package com.me.miguelferreira.platform.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Assignment extends BaseEntity {

    @Embedded
    private GeographicalLocation geographicalLocation = new GeographicalLocation();
    @Embedded
    private Address address = new Address();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
