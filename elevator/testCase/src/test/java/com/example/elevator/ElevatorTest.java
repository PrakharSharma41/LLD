package com.example.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
        private Elevator elevator;

    @BeforeEach
    void setUp() {
        elevator = new Elevator(Direction.IDLE, 0);
    }

    @Test
    void testElevatorInitialization() {
        assertEquals(Direction.IDLE, elevator.getDirection());
        assertEquals(0, elevator.getCurrentFloor());
    }

    @Test
    void testSetDirection() {
        elevator.setDirection(Direction.UP);
        assertEquals(Direction.UP, elevator.getDirection());
    }

    @Test
    void testSetCurrentFloor() {
        elevator.setCurrentFloor(5);
        assertEquals(5, elevator.getCurrentFloor());
    }
}
