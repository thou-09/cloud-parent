package com.itany.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * UpdateTask.
 *
 * @author Thou
 * @date 2022/10/27
 */
@Component
@EnableAsync
@EnableScheduling
public class UpdateTask {

    @Async("taskExecutor")
    // @Scheduled(cron = )
    public void updateSolr() {

    }
}
