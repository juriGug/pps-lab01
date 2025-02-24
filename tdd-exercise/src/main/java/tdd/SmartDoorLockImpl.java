package tdd;

public class SmartDoorLockImpl implements SmartDoorLock{

    private int pin;
    private boolean lock = false;

    @Override
    public void setPin(int pin) {
        if(!isLocked() && pin > 999 && pin < 10000)
            this.pin = pin;
    }

    @Override
    public void unlock(int pin) {

    }

    @Override
    public void lock() {
        if(pin != 0)
            lock = true;
        else
            throw new IllegalStateException("You must have set the pin");
    }


    @Override
    public boolean isLocked() {
        return lock;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
