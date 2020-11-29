package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Controller
public class ChannelUseCase {


    @MessageMapping("route.channel")
    public Flux<List<String>> streamEcho(Flux<Message> message) {
        return message
                .doOnNext(setting -> System.out.println("Requested interval is {} seconds. " + setting))
                .doOnCancel(() -> System.out.println("The client cancelled the channel."))
                .switchMap(msg -> Flux.interval(Duration.ofSeconds(msg.rate))
                        .flatMap(tick -> createMessages(msg)));

    }

    public Flux<List<String>> createMessages(Message baseMessage) {
        return Flux.from(Flux.range(0, baseMessage.rate)
                .map(value -> baseMessage.message)
                .collectList());
    }
}


class Message {
    public String message;
    public Integer rate;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", rate=" + rate +
                '}';
    }
}
