package org.example.orderservice.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.tools.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.OrderDTO;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final RabbitMessagingTemplate rabbitMessagingTemplate;
    private final ObjectMapper mapper;
    @PostMapping(value = "/api/orders",consumes = "application/json",produces = "application/json")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) throws JsonProcessingException {
        String payload = mapper.writeValueAsString(orderDTO);
        rabbitMessagingTemplate.convertAndSend("order-topic","order.created",payload);
        return orderDTO;
    }
}
