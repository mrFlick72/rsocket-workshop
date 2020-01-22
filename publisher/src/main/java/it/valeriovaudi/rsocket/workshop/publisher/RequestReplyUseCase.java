package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/*
TODO
For this task you should implement an echo use case that resend to the sender the same sent message.
Implements a simple echo that once received a message republish inbound message as response using the request reply interaction model
 */
@Controller
public class RequestReplyUseCase {

    @MessageMapping("route.request.reply")
    public Mono<String> echo(String message){
        return Mono.just(message);
    }

}
