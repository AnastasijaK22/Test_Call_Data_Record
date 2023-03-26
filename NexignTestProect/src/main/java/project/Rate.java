package project;

public enum Rate {
    UNLIMITED("06", 300, 0.0, 1.0, false),
    MINUTE("03", 0, 0.0, 1.5, false),
    COMMON("11", 100, 0.5, 1.5, true);

    private String code;
    private long threshold;
    private double costBeforeTh;
    private double costAfterTh;
    private boolean incomingFree;
    Rate(String code, long threshold, double costBeforeTh,
         double costAfterTh, boolean incomingFree) {
        this.code = code;
        this.threshold = threshold;
        this.costBeforeTh = costBeforeTh;
        this.costAfterTh = costAfterTh;
        this.incomingFree = incomingFree;
    }

    public String getCode() {
        return this.code;
    }

    public long getThreshold() {
        return this.threshold;
    }

    public double getCostBeforeTh() {
        return this.costBeforeTh;
    }

    public double getCostAfterTh() {
        return this.costAfterTh;
    }

    public boolean isIncomingFree() {
        return this.incomingFree;
    }
}
