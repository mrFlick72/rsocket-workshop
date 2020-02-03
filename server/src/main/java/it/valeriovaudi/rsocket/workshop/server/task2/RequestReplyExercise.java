package it.valeriovaudi.rsocket.workshop.server.task2;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class RequestReplyExercise {

    @MessageMapping("route.request.reply")
    public Mono<String> echo(String message){
        return Mono.just(message);
    }

}