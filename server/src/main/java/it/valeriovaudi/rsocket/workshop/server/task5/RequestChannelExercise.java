package it.valeriovaudi.rsocket.workshop.server.task5;

import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class RequestChannelExercise {

    private AtomicInteger requestRate = new AtomicInteger(1);

    @MessageMapping("route.request.channel")
    public void ackCollector(Integer size) {
        System.out.println("size: " + size);
        requestRate.set(size);
    }


    @MessageMapping("route.request.channel")
    public Flux<List<String>> streamEcho(String message) {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> createMessages(message));
    }

    public Flux<List<String>> createMessages(String baseMessage) {
        return Flux.just(IntStream.range(0, requestRate.get())
                .mapToObj(value -> baseMessage)
                .collect(Collectors.toList()));
    }
}