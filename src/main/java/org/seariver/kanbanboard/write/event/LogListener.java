package org.seariver.kanbanboard.write.event;

import org.seariver.kanbanboard.write.domain.exception.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    Logger logger = LoggerFactory.getLogger(LogListener.class);

    @Async
    @EventListener
    void onSuccess(CommandEvent event) {

        if (event.isSuccess()) {
            logger.info(event.toJson());
        } else if (event.getException() instanceof DomainException) {
            logger.warn(event.toJson());
        } else {
            logger.error(event.toJson());
        }
    }
}
