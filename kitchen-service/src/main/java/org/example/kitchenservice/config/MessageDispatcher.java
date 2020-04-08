package org.example.kitchenservice.config;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MessageDispatcher {

    public void onMessage(String message) {
        log.info("Recieved message {}", message);
    }
}
