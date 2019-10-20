package com.thoughtworks.parking_lot.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ParkingLot {

    @Id
    @Column(unique = true)
    @NotNull
    private String name;

    private Integer capacity;

    @NotNull
    private String location;

    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingOrders> parkingOrders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<ParkingOrders> getParkingOrders() {
        return parkingOrders;
    }

    public void setParkingOrders(List<ParkingOrders> parkingOrders) {
        this.parkingOrders = parkingOrders;
    }
}
