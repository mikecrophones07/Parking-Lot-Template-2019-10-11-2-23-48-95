package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/parkingLots")
public class ParkingLotController {

    public static final String CANNOT_FOUND_ENTITY_UPON_VALIDATION = "Cannot found Entity upon validation";

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping(produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ParkingLot addParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.save(parkingLot);
    }

    @DeleteMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity<ParkingLot> deleteParkingLot(@PathVariable String name) throws NotFoundException {
        ParkingLot deletedParkingLot = parkingLotService.delete(name);
        if(Objects.nonNull(deletedParkingLot)){
            return new ResponseEntity<>(deletedParkingLot, HttpStatus.CREATED);
        }
        throw new NotFoundException(CANNOT_FOUND_ENTITY_UPON_VALIDATION);
    }

    @GetMapping(value = "/all", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<ParkingLot> getAllParkingLots(@RequestParam Integer page,
                                                  @RequestParam Integer pageSize) {
        return parkingLotService.getAllParkingLots(page, pageSize);
    }

    @GetMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity<ParkingLot> getSpecificParkingLot(@PathVariable String name) {
        ParkingLot fetchedParkingLot = parkingLotService.getSpecificParkingLot(name);
        return new ResponseEntity<>(fetchedParkingLot, HttpStatus.OK);
    }
}
