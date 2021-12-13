package example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.UUID;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uk.co.dave.consumer.fxrate.consumer.avro.AvroFxRateEvent;

@RestController
public class DaveController {

  private StreamBridge streamBridge;

  public DaveController(StreamBridge streamBridge) {
    super();
    this.streamBridge = streamBridge;
  }

  @RequestMapping("/dave")
  public Mono<Map<String, String>> dave() {
    return Mono.just(MessageBuilder.withPayload(AvroFxRateEvent.newBuilder().setFrom("GBP").setTo("USD").setRate(BigDecimal.valueOf(0.109631).setScale(6, RoundingMode.HALF_UP)).build())
        .setHeaderIfAbsent(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString()).build()).flatMap(kafkaEvent -> {
          streamBridge.send("test-out-0", kafkaEvent);
          return Mono.just(Map.of("dave", "melia"));
        });

  }

}
