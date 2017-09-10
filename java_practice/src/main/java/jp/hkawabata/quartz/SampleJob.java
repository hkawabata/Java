package jp.hkawabata.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob implements Job {
    public SampleJob () {}

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(Thread.currentThread().getName() + " : " + getClass().getSimpleName() + " is executed.");
    }
}
