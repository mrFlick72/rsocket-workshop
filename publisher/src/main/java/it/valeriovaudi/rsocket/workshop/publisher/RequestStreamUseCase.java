package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class RequestStreamUseCase {

    @MessageMapping("route.request.and.stream")
    public Flux<String> streamEcho(String message) {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> Flux.just(message));
    }
}
