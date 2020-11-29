package it.valeriovaudi.rsocket.workshop.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Controller
public class ChannelUseCase implements ApplicationRunner {


    Random random = new Random();
    private final Mono<RSocketRequester> requester;
    private final ObjectMapper objectMapper;

    public ChannelUseCase(RSocketRequester.Builder builder, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
                .subscribe(req -> channelFor(req, rate())
                        .flatMap(list -> channelFor(req, rate()))
                        .subscribe(System.out::println));
    }

    private int rate() {
        return random.nextInt(3) + 1;
    }

    private Flux<List> channelFor(RSocketRequester req, int i) {
        return req.route("route.channel")
                .data(Flux.just(new Message("Hey! hello man!", i)))
                .retrieveFlux(List.class);
    }

}


class Message {
    public String message;
    public Integer rate;

    public Message() {
    }

    public Message(String message,
                   Integer rate) {
        this.message = message;
        this.rate = rate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}

