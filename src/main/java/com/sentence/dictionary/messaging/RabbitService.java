package com.sentence.dictionary.messaging;


import com.sentence.dictionary.data.WordDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchnage}")
    private String exchange;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;


    public void sendMessage(WordDto wordDto){
        rabbitTemplate.convertAndSend(exchange, routingKey, wordDto);
    }

}
