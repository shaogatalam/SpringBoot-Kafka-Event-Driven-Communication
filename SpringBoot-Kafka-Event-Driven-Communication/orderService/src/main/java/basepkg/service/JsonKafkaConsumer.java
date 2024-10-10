//package basepkg.service;
//import basepkg.dto.OrderNotification;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JsonKafkaConsumer {
//    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
//
//    @Value("${spring.kafka.topic-json.name}")
//    private String topicJsonName;
//
//    @KafkaListener(topics = "order_create", groupId = "myGroup")
//    public void consume(OrderNotification user, Acknowledgment acknowledgment){
//        LOGGER.info(String.format("Message recieved -> %s", user.toString()));
//        acknowledgment.acknowledge();
//    }
//}