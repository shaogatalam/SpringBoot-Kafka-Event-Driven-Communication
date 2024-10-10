package basepkg.controller;

import basepkg.dto.OrderNotification;
import basepkg.dto.OrderRequest;
import basepkg.service.JsonKafkaProducer;
import basepkg.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    public KafkaTemplate<String, OrderNotification> kafkaTemplate;

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private JsonKafkaProducer kafkaProducer;

    public OrderController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/topics")
    public Set<String> listTopics() throws ExecutionException, InterruptedException, ExecutionException {
        ListTopicsResult topics = adminClient.listTopics(new ListTopicsOptions());
        return topics.names().get();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) throws ExecutionException, InterruptedException {

        OrderNotification orderNotification = new OrderNotification();
        orderNotification.setOrderId("orderId");
        orderNotification.setCustomermail(orderRequest.getCustomermail());
        kafkaProducer.sendMessage(orderNotification);

        //kafkaTemplate.send("OrderCreateTopic", orderNotification);
//        try {
//            kafkaTemplate.send("OrderCreateTopic", orderNotification);
//            System.out.println("Message sent successfully");
//        } catch (Exception e) {
//            System.err.println("Failed to send message: " + e.getMessage());
//            e.printStackTrace();
//        }

        //return kafkaProducer.sendMessage(orderNotification);
        //kafkaTemplate.send("OrderCreateTopic", orderNotification);

        //return "done";

        //String responseMessage =
        orderService.placeOrder(orderRequest);
        return "return";
//        if (Objects.equals(responseMessage, "OrderPlaced")) {
//            kafkaTemplate.send("OrderCreateTopic", "OrderCreateData");
//            return ResponseEntity.ok(responseMessage);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
//        }
    }



}
