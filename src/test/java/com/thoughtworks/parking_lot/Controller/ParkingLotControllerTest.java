package com.thoughtworks.parking_lot.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void should_return_status_200_when_delete_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotService.delete("Mall of Asia")).thenReturn(parkingLot);

        ResultActions result = mvc.perform(delete("/parkingLots/{name}", "Mall of Asia")
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }
}
