package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int SMART_LOCK_PIN = 1234;
    public static final int WRONG_PIN = 1111;
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
    public void testMultipleTimesLock() {
        this.smartPort.setPin(SMART_LOCK_PIN);
        this.smartPort.lock();
        for (int i = 0; i < 5; i++)
            this.smartPort.lock();
        assertTrue(this.smartPort.isLocked());
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
    public void testMultipleTimesUnlockLock() {
        this.smartPort.setPin(SMART_LOCK_PIN);
        this.smartPort.lock();
        for (int i = 0; i < 5; i++)
            this.smartPort.unlock(SMART_LOCK_PIN);
        assertFalse
                (this.smartPort.isLocked());
    }

    @Test
    public void testLockDoorBeforeSetPin() {
        assertThrows(IllegalStateException.class, ()-> this.smartPort.lock());
    }

    @Test
    public void testUnlockPortUntilBlockIt() {
        this.smartPort.setPin(SMART_LOCK_PIN);
        this.smartPort.lock();
        for (int i = 0; i < this.smartPort.getMaxAttempts(); i++) {
            this.smartPort.unlock(WRONG_PIN);
        }
        assertEquals(this.smartPort.getMaxAttempts(), this.smartPort.getFailedAttempts());
        assertTrue(this.smartPort.isBlocked());
    }

    @Test
    public void testTryUnlockAfterBlock() {
        this.smartPort.setPin(SMART_LOCK_PIN);
        this.smartPort.lock();
        for (int i = 0; i < this.smartPort.getMaxAttempts(); i++) {
            this.smartPort.unlock(WRONG_PIN);
        }
        this.smartPort.unlock(SMART_LOCK_PIN);
        assertTrue(this.smartPort.isLocked());
    }

    @Test
    public void testBlockPortAndReset() {
        for (int i = 0; i < this.smartPort.getMaxAttempts(); i++) {
            this.smartPort.unlock(WRONG_PIN);
        }
        this.smartPort.reset();
        assertAll(
                () -> assertEquals(0, this.smartPort.getFailedAttempts()),
                () -> assertFalse(this.smartPort.isLocked()),
                () -> assertThrows(IllegalStateException.class, ()-> this.smartPort.lock())

        );
    }
}
