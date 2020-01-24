package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

@Component
public class FireAndForGetUseCase implements ApplicationRunner {

    private final RSocketRequester requester;

    public FireAndForGetUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .block();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        requester.route("route.request.and.forget")
                .data("it is a message fired and the forgotten")
                .send()
                .subscribe(System.out::println);
    }

}