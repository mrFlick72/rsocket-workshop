package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RequestReplyUseCase {

    private final EchoUseCase echoUseCase;

    public RequestReplyUseCase(EchoUseCase echoUseCase) {
        this.echoUseCase = echoUseCase;
    }

    @PostMapping("/echo")
    public Mono<String> echo(@RequestBody String message) {
        return echoUseCase.echo(message);
    }
}

@Service
class EchoUseCase {

    private final Mono<RSocketRequester> requester;

    public EchoUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port);
    }

    public Mono<String> echo(String message) {
        return requester.flatMap(rSocketRequester -> rSocketRequester.route("route.request.reply")
                .data(message)
                .retrieveMono(String.class));
    }
}