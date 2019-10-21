package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Repository.ParkingOrderRepo;
import com.thoughtworks.parking_lot.Entity.ParkingOrders;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ParkingOrdersService {

    private static final String CANNOT_FOUND_ENTITY_UPON_VALIDATION = "Cannot found Entity upon validation";

    @Autowired
    private ParkingOrderRepo parkingOrderRepo;

    @Autowired
    private ParkingLotService parkingLotService;

    public ParkingOrders save(String name, ParkingOrders parkingOrder) throws NotFoundException {
        ParkingLot fetchParkingLots = parkingLotService.getSpecificParkingLot(name);
        if(Objects.nonNull(fetchParkingLots)){
            parkingOrder.setCreationTime(generateCurrentTime());
            parkingOrder.setStatus(true);
            parkingOrder.setParkingLot(fetchParkingLots);
            return parkingOrderRepo.save(parkingOrder);
        }
        throw new NotFoundException(CANNOT_FOUND_ENTITY_UPON_VALIDATION);
    }

    private String generateCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public ParkingOrders updateOrder(String plateNumber) throws NotFoundException {
        ParkingOrders fetchParkingOrder = parkingOrderRepo.findByPlateNumberAndStatus(plateNumber, true);
        if(Objects.nonNull(fetchParkingOrder)) {
            fetchParkingOrder.setStatus(false);
            fetchParkingOrder.setCloseTime(generateCurrentTime());
            return parkingOrderRepo.save(fetchParkingOrder);
        }
        throw new NotFoundException(CANNOT_FOUND_ENTITY_UPON_VALIDATION);
    }
}
