package it.valeriovaudi.rsocket.workshop.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//import org.springframework.context.annotation.Bean;
//import org.springframework.http.MediaType;
//import org.springframework.messaging.rsocket.RSocketRequester;


// TODO 4) enable RSocketRequester on client side


@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
    /*
    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        int port = 7000;
        String host = "localhost";

        return builder
                .dataMimeType(MediaType.APPLICATION_JSON)
                .connectTcp(host, port)
                .block();
    }*/

}
