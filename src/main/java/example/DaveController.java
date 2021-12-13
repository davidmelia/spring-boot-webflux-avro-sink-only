package example;

import java.util.Map;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DaveController {

  public DaveController(StreamBridge streamBridge) {
    super();
  }

  @RequestMapping("/dave")
  public Mono<Map<String, String>> dave() {
    return Mono.just(Map.of("dave", "melia"));


  }

}
