package com.thoughtworks.parking_lot.Controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Dto.ErrorResponse;
import com.thoughtworks.parking_lot.Entity.ParkingLot;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingLotController.class)
@ActiveProfiles(value = "Test")
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    public void should_return_status_created_when_save_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotService.save(parkingLot)).thenReturn(parkingLot);

        ResultActions result = mvc.perform(post("/parkingLots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingLot)));
        result.andExpect(status().isCreated());
    }

    @Test
    public void should_return_status_Ok_when_delete_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotService.delete("Mall of Asia")).thenReturn(parkingLot);

        ResultActions result = mvc.perform(delete("/parkingLots/{name}", "Mall of Asia")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void should_return_status_bad_request_when_delete_parkingLot() throws Exception {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(404);
        errorResponse.setMessage("Cannot found Entity upon validation");

        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotService.delete("Mall of Asia a")).thenReturn(parkingLot);

        ResultActions result = mvc.perform(delete("/parkingLots/{name}", "Mall of Asia")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(errorResponse.getMessage())));
    }


    @Test
    public void should_return_all_parkingLot_given_page_and_page_size() throws Exception {
        Iterable<ParkingLot> parkingLotList = new ArrayList<>();
        when(parkingLotService.getAllParkingLots(1, 5)).thenReturn(parkingLotList);

        ResultActions result = mvc.perform(get("/parkingLots/all")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
                .param("pageSize", "5"));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", is(parkingLotList)));
    }

    @Test
    public void should_return_ok_specific_parkingLot_when_find_parking_lot_by_name() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Mall of Asia");
        when(parkingLotService.getSpecificParkingLot("Mall of Asia")).thenReturn(parkingLot);

        ResultActions result = mvc.perform(get("/parkingLots/{name}", "Mall of Asia")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(parkingLot.getName())));
    }
}
