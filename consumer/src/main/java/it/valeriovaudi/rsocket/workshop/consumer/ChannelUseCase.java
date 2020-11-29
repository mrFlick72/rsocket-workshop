package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
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
                .subscribe(req -> channelFor(req, rate())
                        .switchMap(list -> channelFor(req, rate()))
                        .subscribe((item) -> {
                            System.out.println(LocalDateTime.now());
                            System.out.println(item);
                        }));
    }

    private int rate() {
        return random.nextInt(10) + 1;
    }

    private Flux<List> channelFor(RSocketRequester req, int i) {
        return req.route("route.channel")
                .data(Flux.just(new Message("Hey! hello man! my rate is: " + i, i)))
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

