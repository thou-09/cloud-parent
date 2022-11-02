package com.itany.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * RabbitMqConfig.
 *
 * @author Thou
 * @date 2022/11/1
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    public static final String PAY_EXCHANGE = "pay_exchange";
    public static final String PAY_QUEUE = "pay_queue";
    public static final String PAY_ROUTING_KEY = "pay_routing_key";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    public void createPayQueueAndExchange() {
        log.warn("[RabbitMq] 创建队列和交换机 ==> pay_exchange, pay_queue");
        Queue queue = new Queue(PAY_QUEUE, true, false, false);
        amqpAdmin.declareQueue(queue);
        DirectExchange directExchange = new DirectExchange(PAY_EXCHANGE);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(PAY_ROUTING_KEY));
    }
}
