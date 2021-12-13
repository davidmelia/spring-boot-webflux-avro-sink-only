package example;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.co.dave.consumer.fxrate.consumer.avro.AvroFxRateEvent;

@Component
public class StaticInstrumentEventConsumer {

  @Bean
  public Function<Flux<Message<AvroFxRateEvent>>, Mono<Void>> fxRates() {
    return events -> events.flatMapSequential(event -> {
      System.out.println("***" + event + "***");
      return Mono.empty();
    }, 1).then();
  }

}
