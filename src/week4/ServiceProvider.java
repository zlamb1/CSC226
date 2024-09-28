package week4;

public class ServiceProvider {
    private int elapsedTime;
    private final int minServiceTime;
    private final int maxServiceTime;
    private int serviceDuration;

    public ServiceProvider(int minServiceTime, int maxServiceTime) {
        this.elapsedTime = 0;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.randomizeDuration();
    }

    public boolean doWork() {
        if (this.elapsedTime >= this.serviceDuration) {
            this.elapsedTime = 0;
            this.randomizeDuration();
            return true;
        }
        this.elapsedTime++;
        return false;
    }

    private void randomizeDuration() {
        this.serviceDuration = (int) (this.minServiceTime + (this.maxServiceTime - this.minServiceTime) * Math.random());
    }
}
