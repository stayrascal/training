package com.thoughtworks.batch;

import com.thoughtworks.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("batch")
public class GreetingBatch {

    @Autowired
    private CounterService counterService;

    @Autowired
    private GreetingService greetingService;

    @Scheduled(cron = "${batch.greeting.cron}")
    public void cronJob(){
        counterService.increment("method.invoked.greetingBatch.cronJob");
        // Add scheduled logic here
    }

    @Scheduled(fixedRateString = "${batch.greeting.fixedRate}")
    public void fixedRateJob(){
        counterService.increment("method.invoked.greetingBatch.fixedRateJob");
    }

    @Scheduled(initialDelayString = "${batch.greeting.initialDelay}", fixedDelayString = "${batch.greeting.fixedRate}")
    public void fixedRateJobWithInitialDelay(){
        counterService.increment("method.invoked.greetingBatchBean.fixedRateJobWithInitialDelay");
    }

    @Scheduled(fixedDelayString = "${batch.greeting.fixedDelay}")
    public void fixedDelayJob(){
        counterService.increment("method.invoked.greetingBatchBean.fixedDelayJob");
    }

    @Scheduled(initialDelayString = "${batch.greeting.initialDelay}", fixedDelayString = "${batch.greeting.fixedDelay}")
    public void fixedDelayJobWithInitialDelay(){
        counterService.increment("method.invoked.greetingBatchBean.fixedDelayJobWithInitialDelay");

    }
}
