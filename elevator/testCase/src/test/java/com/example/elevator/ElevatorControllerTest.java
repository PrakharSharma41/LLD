package com.example.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorControllerTest {
        private ElevatorController elevatorController;

    @BeforeEach
    void setUp() {
        elevatorController = new ElevatorController(3);
    }

    @Test
    void testRequestHandler() {
        Request request = new Request(1, 5, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request handledRequest = elevatorController.requestHandler(request);
        assertEquals(request, handledRequest);
    }

    @Test
    void testFindOptimalElevator() {
        Request request = new Request(2, 6, Direction.UP, Location.OUTSIDE_ELEVATOR);
        ElevatorHandler optimalElevator = elevatorController.findOptimalElevatorHandler(request);
        assertNotNull(optimalElevator);
    }
}
