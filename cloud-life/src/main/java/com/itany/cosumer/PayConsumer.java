package com.itany.cosumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itany.conf.RabbitMqConfig;
import com.itany.service.PaymentService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * PayConsumer.
 *
 * @author Thou
 * @date 2022/11/1
 */
@Slf4j
@Component
public class PayConsumer {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = {RabbitMqConfig.PAY_QUEUE}, ackMode = "MANUAL")
    public void process(Channel channel, Message message) {
        String msg = new String(message.getBody());
        log.warn("[RabbitMq pay_queue] 消费信息 ==> " + msg);
        JSONObject jsonObject = JSON.parseObject(msg);
        Integer userid = (Integer)jsonObject.get("userid");
        String no = (String)jsonObject.get("out_trade_no");

        // 修改订单状态
        paymentService.finishOrder(no);
        log.warn("[RabbitMq pay_queue] 信息消费完成");

        // 通过 restTemplate 通知 websocket 给页面发消息
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("http://localhost:9001/ws/payback?userid={1}", String.class, userid);
        log.warn("[RabbitMq pay_queue] ws 响应 ==> " + responseEntity.getBody());
        try {
            // 手动确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.warn("[RabbitMq pay_queue] 手动确认完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
