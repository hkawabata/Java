package jp.hkawabata.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PrintJob implements Job {
    public PrintJob() {}

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(Thread.currentThread().getName() + " : " + getClass().getSimpleName() + " started.");
        System.out.println(Thread.currentThread().getName() + " : " + getClass().getSimpleName() + " finished.");
    }
}
