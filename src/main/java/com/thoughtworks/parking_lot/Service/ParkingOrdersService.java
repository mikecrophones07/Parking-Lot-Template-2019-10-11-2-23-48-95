package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Repository.ParkingOrderRepo;
import com.thoughtworks.parking_lot.Entity.ParkingOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ParkingOrdersService {

    @Autowired
    private ParkingOrderRepo parkingOrderRepo;

    @Autowired
    private ParkingLotService parkingLotService;

    public ParkingOrders save(String name, ParkingOrders parkingOrder) {
        ParkingLot fetchParkingLots = parkingLotService.getSpecificParkingLot(name);
        if(Objects.nonNull(fetchParkingLots)){
            parkingOrder.setCreationTime(generateCurrentTime());
            parkingOrder.setStatus(true);
            parkingOrder.setParkingLot(fetchParkingLots);
            return parkingOrderRepo.save(parkingOrder);
        }
        return null;
    }

    private String generateCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }
}
