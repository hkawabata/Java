package jp.hkawabata.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class QuartzTrialMain {
    public static void main(String[] args) {
        executePrintJob();
        executeLongPrintJob();
    }

    /**
     * シンプルな例
     */
    private static void executePrintJob() {
        JobDetail job = JobBuilder.newJob(PrintJob.class)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(1000)
                        .repeatForever()
                ).build();
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        System.out.println("##### " + job.getJobClass().getSimpleName() + " started #####");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        System.out.println("##### " + job.getJobClass().getSimpleName() + " finished #####");
    }

    /**
     * 次のジョブ開始までに前のジョブが終わらないケース（ジョブが重複する）
     */
    private static void executeLongPrintJob() {
        JobDetail job = JobBuilder.newJob(LongPrintJob.class)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(200)
                        .repeatForever()
                ).build();
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        System.out.println("##### " + job.getJobClass().getSimpleName() + " started #####");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        System.out.println("##### " + job.getJobClass().getSimpleName() + " finished #####");
    }
}
