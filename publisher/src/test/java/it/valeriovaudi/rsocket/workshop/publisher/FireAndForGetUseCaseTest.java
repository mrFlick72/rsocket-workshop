package it.valeriovaudi.rsocket.workshop.publisher;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest(properties = "spring.rsocket.server.port=0")
class FireAndForGetUseCaseTest {

    @Autowired
    private RSocketRequester.Builder requesterBuilder;

    @LocalRSocketServerPort
    private int port;

    @Test
    void happyPath(CapturedOutput capturedOutput) {
        Mono<RSocketRequester> requester = this.requesterBuilder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp("localhost", this.port);

        Mono<Void> fireAndForGetMono = requester.flatMap(req ->
                req.route("route.request.and.forget")
                        .data("Hey! hello man!")
                        .send());

        StepVerifier.create(fireAndForGetMono)
                .verifyComplete();

        Assertions.assertThat(capturedOutput).contains("Hey! hello man!");
    }
}