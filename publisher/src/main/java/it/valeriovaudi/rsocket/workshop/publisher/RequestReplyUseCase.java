package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class RequestReplyUseCase {

    @MessageMapping("route.request.reply")
    public Mono<String> echo(String message){
        return Mono.just(message);
    }

}
