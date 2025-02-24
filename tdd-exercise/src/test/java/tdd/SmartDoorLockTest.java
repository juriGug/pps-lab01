package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    SmartDoorLockImpl smartPort;

    @BeforeEach
    public void createPort() {
        smartPort = new SmartDoorLockImpl();
    }

    @Test
    public void testIsPortLock() {
        assertFalse(smartPort.isLocked());
    }
}
