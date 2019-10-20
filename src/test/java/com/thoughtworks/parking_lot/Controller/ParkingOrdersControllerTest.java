package com.thoughtworks.parking_lot.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Entity.ParkingOrders;
import com.thoughtworks.parking_lot.Service.ParkingOrdersService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingOrdersController.class)
@ActiveProfiles(value = "Test")
public class ParkingOrdersControllerTest {

    private static final String MALL_OF_ASIA = "Mall of Asia";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ParkingOrdersService parkingOrdersService;

    @Test
    public void should_return_status_created_when_save_parking_oder() throws Exception {

        ParkingOrders parkingOrder = new ParkingOrders();
        when(parkingOrdersService.save(MALL_OF_ASIA, parkingOrder)).thenReturn(parkingOrder);

        ResultActions result = mvc.perform(post("/parkingLots/{name}/orders", MALL_OF_ASIA)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingOrder)));
        result.andExpect(status().isCreated());
    }

    @Test
    public void should_return_status_bad_request_when_save_parking_order_with_non_existing_parking_lot() throws Exception {
        ParkingOrders parkingOrder = new ParkingOrders();
        when(parkingOrdersService.save(MALL_OF_ASIA, parkingOrder)).thenReturn(parkingOrder);

        ResultActions result = mvc.perform(post("/parkingLots/{name}/orders", "a")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingOrder)));
        result.andExpect(status().isBadRequest());
    }
}