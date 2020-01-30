package it.valeriovaudi.rsocket.workshop.server.task1;


import org.junit.Test;
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
}
