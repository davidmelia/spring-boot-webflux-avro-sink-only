package uk.co.dave.consumer.fxrate.consumer.avro;

import java.util.List;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StaticInstrumentEventConsumer {


  @Bean
  public Function<Flux<Message<Object>>, Mono<Void>> test() {
    return events -> events.flatMapSequential(eventList -> {
      List<Object> eventsList = (List<Object>) eventList.getPayload();
      System.out.println(eventList);
      return Mono.empty();
    }, 1).then();
  }

}
