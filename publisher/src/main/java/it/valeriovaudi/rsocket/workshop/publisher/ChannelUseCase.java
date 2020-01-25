package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class ChannelUseCase {

    private final Mono<RSocketRequester> requester;

    public ChannelUseCase(RSocketRequester.Builder builder) {
        int port = 7001;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .cache();
    }


    @MessageMapping("route.request.channel")
    public Flux<String> streamEcho(String message) {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> requester.flatMap(rSocketRequester -> rSocketRequester.route("route.request.channel")
                        .data("message  fired")
                        .send()
                        .log())
                        .thenMany(Flux.just(message)));
    }
}
