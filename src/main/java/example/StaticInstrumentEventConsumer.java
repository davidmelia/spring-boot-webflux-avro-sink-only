package example;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StaticInstrumentEventConsumer {



  // @Bean
  // public Consumer<Flux<Message<AvroFxRateEvent>>> fxRates() {
  // return events -> events.subscribe(event -> {
  // System.out.println(event);
  // });
  // }



  @Bean
  public Function<Flux<Message<Object>>, Mono<Void>> fxRates() {
    return events -> events.flatMapSequential(eventList -> {
      System.out.println("***" + eventList + "***");
      return Mono.empty();
    }, 1).then();
  }

}
