package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FireAndForGetUseCase implements ApplicationRunner {

    private final Mono<RSocketRequester> requester;

    public FireAndForGetUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        requester.flatMap(rSocketRequester -> rSocketRequester.route("route.request.and.forget")
        .data("it is a message fired and the forgotten")
        .send())
        .subscribe();
    }
}