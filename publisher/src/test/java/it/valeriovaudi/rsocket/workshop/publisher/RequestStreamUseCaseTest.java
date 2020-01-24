package it.valeriovaudi.rsocket.workshop.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(properties = "spring.rsocket.server.port=0")
class RequestStreamUseCaseTest {


    @Autowired
    private RSocketRequester.Builder requesterBuilder;

    @LocalRSocketServerPort
    private int port;

    @Test
    void happyPath() {
        RSocketRequester requester = this.requesterBuilder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp("localhost", this.port)
                .block();

        Flux<String> flux = requester.route("route.request.and.stream")
                .data("Hey! hello man!")
                .retrieveFlux(String.class);


        StepVerifier.create(flux)
                .expectNext("Hey! hello man!")
                .expectNext("Hey! hello man!")
                .expectNext("Hey! hello man!")
                .expectNext("Hey! hello man!")
                .expectNext("Hey! hello man!")
                .expectNext("Hey! hello man!")
                .thenCancel()
                .verify();
    }
}