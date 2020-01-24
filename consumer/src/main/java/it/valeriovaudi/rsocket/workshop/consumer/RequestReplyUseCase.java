package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class RequestReplyUseCase implements ApplicationRunner {

    private final RSocketRequester requester;

    public RequestReplyUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .block();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(requester.route("route.request.reply")
                .data("this is an echo message")
                .retrieveMono(String.class)
                .block());
    }
}