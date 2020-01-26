package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Random;

@Controller
public class ChannelUseCase implements ApplicationRunner {

    Random random = new Random();
    private final Mono<RSocketRequester> requester;

    public ChannelUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .cache();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        requester
                .flatMap(req -> req.route("route.request.channel")
                        .data("Hey! hello man!")
                        .retrieveFlux(Object.class)
                        .log()
                        .flatMap(strings ->
                                requester.flatMap(rSocketRequester ->
                                        rSocketRequester.route("route.request.channel")
                                                .data(random.nextInt(3) + 1)
                                                .send())
                        )
                        .then())
                .subscribe();
    }
}
