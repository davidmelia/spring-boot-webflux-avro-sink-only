package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyApplication.class, args); //
  }

  // @Bean
  // @Order(value = Ordered.LOWEST_PRECEDENCE)
  // public NettyServerCustomizer nettyServerWarmer() {
  // return httpServer -> {
  // System.out.println("My custom code");
  // return httpServer;
  // };
  // }

}
