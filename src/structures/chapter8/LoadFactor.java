package structures.chapter8;

public class LoadFactor {
    private final boolean useLowerBound, useUpperBound;
    private final double lowerBound, upperBound;

    public LoadFactor(double lowerBound, double upperBound) {
        this(lowerBound, upperBound, true, true);
    }

    public LoadFactor(double lowerBound, double upperBound, boolean useLowerBound, boolean useUpperBound) {
        if (lowerBound < 0 || lowerBound > 1) {
            throw new IllegalArgumentException("Lower bound must be between 0 and 1.");
        }

        if (upperBound < 0 || upperBound > 1) {
            throw new IllegalArgumentException("Upper bound must be between 0 and 1.");
        }

        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound cannot be greater than upper bound.");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.useLowerBound = useLowerBound;
        this.useUpperBound = useUpperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public boolean shouldUseLowerBound() {
        return useLowerBound;
    }

    public boolean shouldUseUpperBound() {
        return useUpperBound;
    }
}
