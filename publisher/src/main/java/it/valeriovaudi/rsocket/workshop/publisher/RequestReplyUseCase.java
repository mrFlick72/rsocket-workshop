package it.valeriovaudi.rsocket.workshop.publisher;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/*
TODO
Implements a simple echo that once received a message republish the inbound message as response
TIPS think to RSocket to messages to exchange and think how Spring manage typically a situation like this.
For instance think how Spring manage websocket
 */
@Controller
public class RequestReplyUseCase {

    @MessageMapping("route.request.reply")
    public Mono<String> echo(String message){
        return Mono.just(message);
    }

}
