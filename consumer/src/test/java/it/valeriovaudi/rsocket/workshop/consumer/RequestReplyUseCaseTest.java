package it.valeriovaudi.rsocket.workshop.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RequestReplyUseCase.class)
class RequestReplyUseCaseTest {

    @MockBean
    private EchoUseCase echoUseCase;

    @Autowired
    private WebTestClient webClient;


    @Test
    void happyPath() {
        Mono<String> just = Mono.just("Hello!");
        given(echoUseCase.echo("Hello!"))
                .willReturn(just);

        webClient.post().uri("/echo")
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello!"))
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("Hello!");


    }

}