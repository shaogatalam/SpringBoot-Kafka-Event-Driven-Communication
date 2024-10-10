//package basepkg.service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//import basepkg.dto.OrderNotification;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//@Service
//public class JsonKafkaProducer {
//
//    @Value("${spring.kafka.topic-json.name}")
//    private String topicJsonName;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
//
//    private KafkaTemplate<String, OrderNotification> kafkaTemplate;
//
//    public JsonKafkaProducer(KafkaTemplate<String, OrderNotification> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public String sendMessage(OrderNotification data){
//
//        LOGGER.info(String.format("Message sent -> %s", data.toString()));
//
//        Message<OrderNotification> message = MessageBuilder
//                .withPayload(data)
//                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
//                .build();
//
//        //kafkaTemplate.send(message);
//        CompletableFuture<SendResult<String, OrderNotification>> future = kafkaTemplate.send(message);
//        CompletableFuture<String> mapped = future
//            .thenApply(result -> {
//                var metadata = result.getRecordMetadata();
//                var partition = metadata.partition();
//                var offset = metadata.offset();
//                LOGGER.info("Sent message=[{}] with offset=[{}]", message, offset);
//                return String.format("%d-%d", partition, offset);
//            }).exceptionally(err -> {
//                LOGGER.info("Unable to send message=[{}] due to : {}", message, err.getMessage());
//                return null;
//            });
//        return "done";
//    }
//}