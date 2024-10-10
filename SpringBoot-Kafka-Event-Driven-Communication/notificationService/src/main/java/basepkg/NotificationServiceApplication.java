package basepkg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class,args);
        System.out.println("Hello from NotificationServiceApplication !");
    }
}

//@Service
//class KafkaMessageListener {
//    @KafkaListener(topics = "OrderCreateTopic",groupId = "Group100",containerFactory = "Listener")
//    public void listen(OrderNotification orderNotification) {
//        System.out.println("Received message: " + orderNotification);
//    }
//}