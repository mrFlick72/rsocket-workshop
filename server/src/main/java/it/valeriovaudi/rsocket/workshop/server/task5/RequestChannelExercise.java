package it.valeriovaudi.rsocket.workshop.server.task5;

import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class RequestChannelExercise {

    private final Mono<RSocketRequester> requester;

    public RequestChannelExercise(RSocketRequester.Builder builder) {
        int port = 7001;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .cache();
    }
}