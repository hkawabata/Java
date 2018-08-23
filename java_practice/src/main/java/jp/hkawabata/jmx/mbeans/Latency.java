package jp.hkawabata.jmx.mbeans;

public class Latency implements LatencyMBean {
    private long counter = 0;
    private double latencySum = 0;
    public void addLatencySum(double latency) {
        counter++;
        latencySum += latency;
    }
    public long getAccessNum() {
        return counter;
    }
    public double getLatencyAvg() {
        return latencySum * 1.0 / counter;
    }
}
