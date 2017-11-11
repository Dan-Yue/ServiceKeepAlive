package com.example.danyu.servicekeepalive.system;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import com.example.danyu.servicekeepalive.util.TimerUtil;


/**
 * Created by danyue on 2017/11/12.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobAwakenTwoService extends JobService {
    @Override
    public void onCreate() {
        super.onCreate();
        TimerUtil.startPolling("JobAwakenTwoService");
        startJobSheduler();
    }

    public void startJobSheduler() {
        try {
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(), JobAwakenService.class.getName()));
            builder.setPeriodic(5);
            builder.setPersisted(true);
            JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(builder.build());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
