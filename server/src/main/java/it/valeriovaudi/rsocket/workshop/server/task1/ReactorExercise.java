package it.valeriovaudi.rsocket.workshop.server.task1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorExercise {

    public static Mono eagerMono() {
        return Mono.empty();
    }

    public static Mono lazyMono() {
        return Mono.empty();
    }

    public static Flux firstTenElementOfAnInfiniteFlux() {
        return Flux.empty();
    }

    public static Flux composableFlux(Flux<String> aFlux, Flux<String> anotherFlux) {
        return Flux.empty();
    }

}
