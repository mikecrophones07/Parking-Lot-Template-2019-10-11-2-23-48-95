package com.thoughtworks.parking_lot.Repository;

import com.thoughtworks.parking_lot.Entity.ParkingOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingOrderRepo extends JpaRepository<ParkingOrders, String> {

    ParkingOrders findByPlateNumberAndStatus(String plateNumber, Boolean status);
}
