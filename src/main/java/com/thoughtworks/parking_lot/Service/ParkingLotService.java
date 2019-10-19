package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepo parkingLotRepo;

    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepo.save(parkingLot);
    }

    public ParkingLot delete(String name) {
        ParkingLot fetchedParkingLot = parkingLotRepo.findByName(name);
        parkingLotRepo.delete(fetchedParkingLot);
        return fetchedParkingLot;
    }
}
