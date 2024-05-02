package org.yixz.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.yixz.common.util.ThreadUtil;

import java.time.Duration;

@Configuration
@Slf4j
public class RedisStreamConfig {
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public StreamMessageListenerContainer<String, ObjectRecord<String, String>> streamMessageListenerContainer(
            RedisConnectionFactory connectionFactory, RedisConsumer redisConsumer) {

        // 用于配置消息监听容器的选项。在这个方法中，通过设置不同的选项，如轮询超时时间和消息的目标类型，可以对消息监听容器进行个性化的配置。
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .batchSize(1)
                        // 设置了轮询超时的时间为100毫秒。这意味着当没有新的消息时，容器将每隔100毫秒进行一次轮询。
                        .pollTimeout(Duration.ofMillis(100))
                        .executor(ThreadUtil.POOL_TASK_EXCUTOR)
                        // 指定了消息的目标类型为 String。这意味着容器会将接收到的消息转换为 String 类型，以便在后续的处理中使用。
                        .targetType(String.class)
                        .build();

        //初始化stream
        initializeStream();

        // 创建一个可用于监听Redis流的消息监听容器。
        StreamMessageListenerContainer<String, ObjectRecord<String, String>> listenerContainer =
                StreamMessageListenerContainer.create(connectionFactory, options);

        //指定从哪里开始消费
        StreamOffset<String> offset = StreamOffset.create("test-stream", ReadOffset.lastConsumed());
        //消费者
        Consumer consumer = Consumer.from("test-consumer-group", "test-consumer-name");

        //创建消费者,接收上次处理未ACK消费的消息,指定消费者对象,autoAcknowledge 关闭自动ack确认
        listenerContainer.register(StreamMessageListenerContainer.StreamReadRequest
                .builder(offset)
                .errorHandler((error) -> log.info("消费消息失败", error))
                //报错之后继续接收消息
                .cancelOnError(e -> false)
                .consumer(consumer)
                .autoAcknowledge(true)
                .build(), redisConsumer);

        // 方法启动了消息监听容器，使其开始监听消息。一旦容器被启动，它将开始接收并处理来自Redis流的消息。
        listenerContainer.start();
        return listenerContainer;
    }

    public void initializeStream() {
        StreamOperations<String, Object, Object> streamOperations = redisTemplate.opsForStream();
        // 创建一个流
        try {
            log.warn("开始创建消费者组");
            streamOperations.createGroup("test-stream", ReadOffset.from("0"), "test-consumer-group");
        } catch (Exception e) {
            // 流可能已存在，忽略异常
            log.warn("消费者组已创建过");
        }
    }
}
