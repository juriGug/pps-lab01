package tdd;

public class SmartDoorLockImpl implements SmartDoorLock{

    private int pin;
    private boolean lock = false;
    private boolean block = false;
    private int counterFailedAttempts = 0;
    public static final int MAX_ATTEMPTS = 5;

    @Override
    public void setPin(int pin) {
        if(!isLocked() && pin > 999 && pin < 10000)
            this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if(this.pin == pin) {
            lock = false;
            counterFailedAttempts = 0;
        }
        else {
            this.counterFailedAttempts++;
            if(counterFailedAttempts == MAX_ATTEMPTS)
                block = true;
        }
    }

    @Override
    public void lock() {
        if(this.pin != 0)
            this.lock = true;
        else
            throw new IllegalStateException("You must have set the pin");
    }


    @Override
    public boolean isLocked() {
        return this.lock;
    }

    @Override
    public boolean isBlocked() {
        return block;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return counterFailedAttempts;
    }

    @Override
    public void reset() {

    }
}
