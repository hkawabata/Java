package jp.hkawabata.jmx;

import java.lang.management.*;
import javax.management.*;

import jp.hkawabata.jmx.mbeans.Latency;

public class Main {
    public static void main(String[] args) throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("com.example.mbeans:type=Latency");

        Latency mbean = new Latency();

        mbs.registerMBean(mbean, name);

        System.out.println("Waiting forever...");
        while (true) {
            mbean.addLatencySum(Math.random());
            Thread.sleep(1000);
        }
    }
}
