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
        assertFalse(this.smartPort.isLocked());
    }

    @Test
    public void testSetPinAndLockAndUnlockPort() {
        this.smartPort.setPin(SMART_LOCK_PIN);
        this.smartPort.lock();
        assertTrue(smartPort.isLocked());
        this.smartPort.unlock(SMART_LOCK_PIN);
        assertFalse(smartPort.isLocked());
    }

    @Test
    public void testExceptionLock() {
        assertThrows(IllegalStateException.class, ()-> this.smartPort.lock());
    }

    @Test
    public void testUnlockPort() {
    }
}
