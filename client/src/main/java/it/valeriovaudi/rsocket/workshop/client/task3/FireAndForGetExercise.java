package it.valeriovaudi.rsocket.workshop.client.task3;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;


@Component
public class FireAndForGetExercise implements ApplicationRunner {

    private final RSocketRequester requester;

    public FireAndForGetExercise(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .block();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO insert here your solution and pay attention to the log
        // remember to start the publisher app before
    }
}