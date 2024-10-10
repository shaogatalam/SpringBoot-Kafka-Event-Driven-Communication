package basepkg.service;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import basepkg.dto.OrderNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerService.class);

    @KafkaListener(topics = "order_create", groupId = "newGroup")
    public void consume(OrderNotification user, Acknowledgment acknowledgment){
        LOGGER.info(String.format("Message recieved -> %s", user.toString()));
        acknowledgment.acknowledge();
    }
}
