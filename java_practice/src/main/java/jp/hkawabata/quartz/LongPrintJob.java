package jp.hkawabata.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * sleep しつつ複数回 print を実行する
 */
public class LongPrintJob implements Job {
    public LongPrintJob() {}

    private static final int intervalMillis = 500;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(Thread.currentThread().getName() + " : " + getClass().getSimpleName() + " started.");
        try {
            Thread.sleep(intervalMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " : " + getClass().getSimpleName() + " finished.");
    }
}
