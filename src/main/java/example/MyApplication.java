package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class MyApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyApplication.class, args); //
  }

  @Bean
  @Order(value = Ordered.LOWEST_PRECEDENCE)
  public NettyServerCustomizer nettyServerWarmer() {
    return httpServer -> {
      System.out.println("My custom code");
      return httpServer;
    };
  }

}
