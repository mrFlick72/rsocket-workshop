package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ChannelUseCase implements ApplicationRunner {

  /*  private final Mono<RSocketRequester> requester;

    public ChannelUseCase(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        this.requester = builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .cache();
    }*/


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO insert here your solution and pay attention to the log
        // remember to start the publisher app before
    }

}