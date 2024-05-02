package org.yixz.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisProducer {
    @Autowired
    private RedisTemplate redisTemplate;


    public void sendMessage(String message) {
        ObjectRecord<String, String> record= StreamRecords.newRecord().in("test-stream")
                .ofObject(message);

        RecordId recordId = redisTemplate.opsForStream().add(record);
        if (recordId != null) {
            log.info("发送消息， messageId：{}, body：{}", recordId, message);
        }
    }
}
