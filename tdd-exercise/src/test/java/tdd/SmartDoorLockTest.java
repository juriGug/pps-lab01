package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int SMART_LOCK_PIN = 1234;
    SmartDoorLockImpl smartPort;

    @BeforeEach
    public void createPort() {
        smartPort = new SmartDoorLockImpl();
    }

    @Test
    public void testIsPortLock() {
        assertFalse(smartPort.isLocked());
    }

    @Test
    public void testSetPin() {
        assertFalse(smartPort.isLocked());
        smartPort.setPin(SMART_LOCK_PIN);
        smartPort.lock();
        assertTrue(smartPort.isLocked());
    }
}
