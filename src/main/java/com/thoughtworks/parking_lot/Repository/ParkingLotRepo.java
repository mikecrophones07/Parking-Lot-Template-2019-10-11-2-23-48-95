package com.thoughtworks.parking_lot.Repository;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {

    ParkingLot findByName(String name);
}
