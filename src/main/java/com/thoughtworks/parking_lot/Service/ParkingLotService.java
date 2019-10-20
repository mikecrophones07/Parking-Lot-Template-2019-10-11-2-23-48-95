package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Dto.StatusResponse;
import com.thoughtworks.parking_lot.Dto.TypeValuePairs;
import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepo parkingLotRepo;

    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepo.save(parkingLot);
    }

    public ParkingLot delete(String name) {
        ParkingLot fetchedParkingLot = parkingLotRepo.findByName(name);
        if(Objects.nonNull(fetchedParkingLot)){
            parkingLotRepo.delete(fetchedParkingLot);
            return fetchedParkingLot;
        }
        return null;
    }

    public Iterable<ParkingLot> getAllParkingLots(Integer page, Integer pageSize) {
        return parkingLotRepo.findAll(PageRequest.of(page, pageSize));
    }

    public ParkingLot getSpecificParkingLot(String name) {
        ParkingLot fetchedParkingLot = parkingLotRepo.findByName(name);
        if(Objects.nonNull(fetchedParkingLot)){
            return fetchedParkingLot;
        }
        return null;
    }

    public StatusResponse updateCapacity(String name, Integer capacity) {
        StatusResponse response = new StatusResponse();
        TypeValuePairs valuePair = new TypeValuePairs();

        ParkingLot fetchedParkingLot = parkingLotRepo.findByName(name);
        if(Objects.nonNull(fetchedParkingLot)) {
            fetchedParkingLot.setCapacity(capacity);
            parkingLotRepo.save(fetchedParkingLot);

            valuePair.setType("Capacity");
            valuePair.setValue(capacity.toString());
            response.setStatusMsg("Success updating capacity");
            response.setStatusCode(200);
            response.setTypeValuePairs(Collections.singletonList(valuePair));
            return response;
        }
        return null;
    }
}
