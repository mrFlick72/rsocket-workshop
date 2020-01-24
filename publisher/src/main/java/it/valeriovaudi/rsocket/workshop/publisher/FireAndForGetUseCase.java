package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class FireAndForGetUseCase {

    @MessageMapping("route.request.and.forget")
    public Mono<Void> fireAndForGet(String message) {
        System.out.println(message);
        return Mono.empty();
    }
}
