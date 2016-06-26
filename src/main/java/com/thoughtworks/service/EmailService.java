package com.thoughtworks.service;

import com.thoughtworks.model.Greeting;
import com.thoughtworks.util.AsyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    public Boolean send(Greeting greeting) {
        logger.info("> send");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Processing time was {} seconds.", 5);
        logger.info("< send");
        return Boolean.TRUE;
    }

    @Async
    public void sendAsync(Greeting greeting) {
        logger.info("> sendAsync");
        try {
            send(greeting);
        } catch (Exception e) {
            logger.warn("Exception caught sending asynchronous mail.", e);
        }

        logger.info("< sendAsync");
    }

    @Async
    public Future<Boolean> sendAsyncWithResult(Greeting greeting) {
        logger.info("> sendAsyncWithResult");

        AsyncResponse<Boolean> response = new AsyncResponse<>();

        try {
            Boolean success = send(greeting);
            response.complete(success);
        } catch (Exception e) {
            logger.warn("Exception caught sending asynchronous mail.", e);
            response.completedExceptionally(e);

        }

        logger.info("< sendAsyncWithResult");
        return response;
    }
}
