package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

@Component
public class RequestStreamUseCase implements ApplicationRunner {

    private final RSocketRequester requester;

    public RequestStreamUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .block();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        requester.route("route.request.and.stream")
                .data("Hey! hello man!")
                .retrieveFlux(String.class)
//                .take(10)
                .subscribe(System.out::println);
    }
}
