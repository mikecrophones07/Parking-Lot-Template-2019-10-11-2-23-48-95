package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Entity.ParkingOrders;
import com.thoughtworks.parking_lot.Service.ParkingOrdersService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "parkingLots")
public class ParkingOrdersController{

    @Autowired
    private ParkingOrdersService parkingOrderService;

    @PostMapping(value = "/{name}/orders", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ParkingOrders> addParkingLot(@PathVariable String name,
                                                       @RequestBody ParkingOrders parkingOrders)
            throws NotFoundException {
        ParkingOrders savedParkingOrder = parkingOrderService.save(name, parkingOrders);
        return new ResponseEntity<>(savedParkingOrder, HttpStatus.OK);

    }

    @PatchMapping(value = "/orders/{plateNumber}", produces = {"application/json"})
    public ResponseEntity<ParkingOrders> updateCapacityOfParkingLot(@PathVariable String plateNumber) throws NotFoundException {
        ParkingOrders resultParkingOrders = parkingOrderService.updateOrder(plateNumber);
        return new ResponseEntity<>(resultParkingOrders, HttpStatus.OK);
    }

}
