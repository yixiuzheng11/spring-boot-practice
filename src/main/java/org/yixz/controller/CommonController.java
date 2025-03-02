package org.yixz.controller;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yixz.common.util.RedisUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private RedisUtil redisUtil;

    private final ConcurrentHashMap<String, SseEmitter> sseMap = new ConcurrentHashMap<>();

    @Operation(summary = "sse关闭")
    @GetMapping("/stopSse")
    public void stopSse(String key) {
        SseEmitter emitter = sseMap.get(key);
        if(emitter!=null) {
            emitter.complete();
        }
    }

    @Operation(summary = "sse流式接口")
    @GetMapping("/sse")
    public SseEmitter sse() {
        String key = "sse_" + UUID.fastUUID();
        log.info("创建的sse：{}", key);
        SseEmitter emitter = new SseEmitter(60*1000L);
        sseMap.put(key, emitter);
        emitter.onCompletion(() -> {
            // 可在此处释放资源
            log.info("SSE 结束");
            sseMap.remove(key);
        });
        emitter.onTimeout(() -> {
            // 可在此处释放资源
            log.info("sse 超时");
            sseMap.remove(key);
        });
        emitter.onError(e -> {
            log.error("sse 发生错误", e);
            sseMap.remove(key);
        });
        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    String data = "Data batch 第" + i;
                    log.info("发送数据：{}", data);
                    emitter.send(SseEmitter.event()
                            .id(String.valueOf(System.currentTimeMillis()))
                            .name("chunk_message")
                            .data(data));
                    // 模拟批量数据之间的延迟
                    Thread.sleep(500);
                }
                // 完成SSE连接
                emitter.complete();
            } catch (Exception e) {
                // 处理错误
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }
}
