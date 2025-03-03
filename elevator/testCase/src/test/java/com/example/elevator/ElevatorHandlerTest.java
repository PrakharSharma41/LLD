package com.example.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorHandlerTest {
    private ElevatorHandler elevatorHandler;

    @BeforeEach
    void setUp() {
        elevatorHandler = new ElevatorHandler(1, 0);
    }

    @Test
    void testElevatorHandlerInitialization() {
        assertNotNull(elevatorHandler.elevator);
        assertEquals(Direction.IDLE, elevatorHandler.elevator.getDirection());
        assertEquals(0, elevatorHandler.elevator.getCurrentFloor());
    }

    @Test
    void testSendUpRequest() {
        Request request = new Request(0, 5, Direction.UP, Location.OUTSIDE_ELEVATOR);
        elevatorHandler.sendUpRequest(request);
        assertFalse(elevatorHandler.upQueue.isEmpty());
    }

    @Test
    void testSendDownRequest() {
        Request request = new Request(5, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        elevatorHandler.sendDownRequest(request);
        assertFalse(elevatorHandler.downQueue.isEmpty());
    }

    @Test
    void testRun() {
        Request request1 = new Request(0, 5, Direction.UP, Location.OUTSIDE_ELEVATOR);
        Request request2 = new Request(5, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);
        elevatorHandler.sendUpRequest(request1);
        elevatorHandler.sendDownRequest(request2);
        
        elevatorHandler.run();
        
        assertEquals(Direction.IDLE, elevatorHandler.elevator.getDirection());
    }
}
