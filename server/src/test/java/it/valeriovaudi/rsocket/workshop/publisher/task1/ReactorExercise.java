package it.valeriovaudi.rsocket.workshop.publisher.task1;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(OutputCaptureExtension.class)
public class ReactorExercise {

    @Test
    void eagerMono(CapturedOutput capturedOutput) {
        Mono<String> anEagerMono = Mono.just("an eager mono");

        StepVerifier.create(anEagerMono)
                .expectNext("an eager mono")
                .verifyComplete();

        anEagerMono.subscribe();
    }
}
