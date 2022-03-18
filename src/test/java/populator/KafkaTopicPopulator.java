package populator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import uk.co.dave.consumer.fxrate.consumer.avro.AvroFxRateEvent;

@SpringBootTest
@ActiveProfiles("populator")
public class KafkaTopicPopulator {

  @Autowired
  private StreamBridge streamBridge;
  
  @Test
  void testName() throws Exception {
     Mono.just(MessageBuilder.withPayload(AvroFxRateEvent.newBuilder().setFrom("GBP").setTo("USD").setRate(BigDecimal.valueOf(0.109631).setScale(6, RoundingMode.HALF_UP)).build())
        .setHeaderIfAbsent(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString()).build()).flatMap(kafkaEvent -> {
          System.out.println("sending");
          streamBridge.send("fxRates-out-0", kafkaEvent);
          return Mono.just(Map.of("dave", "melia"));
        }).repeat(10).collectList().map(m -> m.get(0)).block();
  }
}
