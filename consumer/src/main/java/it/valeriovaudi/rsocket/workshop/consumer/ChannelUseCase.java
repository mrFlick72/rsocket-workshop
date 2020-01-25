package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class ChannelUseCase implements ApplicationRunner {

    private final Mono<RSocketRequester> requester;

    public ChannelUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .cache();
    }

    @MessageMapping("route.request.channel")
    public Mono<Void> ackCollector(String ack) {
        return Mono.just(ack).log().then();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        requester
                .flatMap(req -> req.route("route.request.channel")
                        .data("Hey! hello man!")
                        .retrieveFlux(String.class)
                        .take(10)
                        .log()
                        .then())
                .subscribe();

    }
}
