package basepkg.config;
import java.util.HashMap;
import java.util.Map;
import basepkg.dto.OrderNotification;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class Consumerkonfig {


    @Bean
    public ConsumerFactory<String, OrderNotification> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "newGroup"); // use a new group for testing
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "basepkg.dto");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(OrderNotification.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderNotification> kafkaListenerContainerFactory(ConsumerFactory<String, OrderNotification> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, OrderNotification> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }


//    public static final String GROUP_ID = "Group100";

//    @Bean
//    public ConsumerFactory<String, OrderNotification> consumerFactory() {
//
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(OrderNotification.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, OrderNotification> Listener() {
//        ConcurrentKafkaListenerContainerFactory<String, OrderNotification> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

//    @Bean
//    public ConsumerFactory<String, OrderNotification> consumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "Group100");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, OrderNotification.class.getName());
//
//        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new JsonDeserializer<>(OrderNotification.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, OrderNotification> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, OrderNotification> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

}
