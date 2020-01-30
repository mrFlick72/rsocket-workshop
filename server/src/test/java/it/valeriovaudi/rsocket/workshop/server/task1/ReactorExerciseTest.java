package it.valeriovaudi.rsocket.workshop.server.task1;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactorExerciseTest {

    @Test
    public void eagerMono() {
        Mono<String> anEagerMono = ReactorExercise.eagerMono();

        StepVerifier.create(anEagerMono)
                .expectNext("an eager mono")
                .verifyComplete();

        anEagerMono.subscribe();
    }

    @Test
    public void lazyMono() {
        Mono<String> anEagerMono = ReactorExercise.lazyMono();

        StepVerifier.create(anEagerMono)
                .expectNext("an eager mono")
                .verifyComplete();
    }


    @Test
    public void firstTenElementOfAnInfiniteFlux() {
        Flux<String> firstTenElementOfAnInfiniteFlux = ReactorExercise.firstTenElementOfAnInfiniteFlux();

        StepVerifier.create(firstTenElementOfAnInfiniteFlux)
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .expectNext("an eager mono")
                .verifyComplete();
    }

    @Test
    public void composableFlux() {
        Flux<String> firstTenElementOfAnInfiniteFlux =
                ReactorExercise.composableFlux(Flux.just("a", "b", "c"), Flux.just("c", "b", "a"));

        StepVerifier.create(firstTenElementOfAnInfiniteFlux)
                .expectNext("ac")
                .expectNext("bb")
                .expectNext("ca")
                .verifyComplete();
    }


}
