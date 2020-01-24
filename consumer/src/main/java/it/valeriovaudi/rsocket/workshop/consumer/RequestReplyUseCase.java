package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RequestReplyUseCase implements ApplicationRunner {

    private final Mono<RSocketRequester> requester;

    public RequestReplyUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO insert here your solution and pay attention to the log
        // remember to start the publisher app before
    }
}