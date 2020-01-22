package it.valeriovaudi.rsocket.workshop.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.rsocket.server.port=0")
class RequestReplyUseCaseTest {

    @Autowired
    private RSocketRequester.Builder requesterBuilder;

    @LocalRSocketServerPort
    private int port;

    @Test
    void happyPath(){
        Mono<RSocketRequester> requester = this.requesterBuilder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp("localhost", this.port);

        Mono<String> echoPublisher = requester.flatMap(req ->
                req.route("route.request.reply")
                        .data("Hey! hello man!")
                        .retrieveMono(String.class));

        StepVerifier.create(echoPublisher)
                .assertNext(message -> {
                    assertEquals(message, "Hey! hello man!");
                })
                .verifyComplete();
    }
}