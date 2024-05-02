package org.yixz.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisConsumer implements StreamListener<String, ObjectRecord<String, String>> {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(ObjectRecord<String, String> message) {
        String stream = message.getStream();
        String messageId = message.getId().toString();
        String messageBody = message.getValue();
        log.info("Received message from stream: {}, messageId: {}，body：{} ",stream,messageId, messageBody);
        if(messageBody.equals("\"50\"")) {
            System.out.println(1/0);
        }
        redisTemplate.opsForStream().delete(message);
    }
}
