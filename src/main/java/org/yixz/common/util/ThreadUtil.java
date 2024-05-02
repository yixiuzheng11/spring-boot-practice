package org.yixz.common.util;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadUtil {

    public static ThreadPoolTaskExecutor POOL_TASK_EXCUTOR = null;

    static {
        POOL_TASK_EXCUTOR = new ThreadPoolTaskExecutor();
        POOL_TASK_EXCUTOR.setCorePoolSize(2);//核心线程数
        POOL_TASK_EXCUTOR.setMaxPoolSize(2);//最大线程数
        POOL_TASK_EXCUTOR.setQueueCapacity(10);//线程队列
        POOL_TASK_EXCUTOR.initialize();//线程初始化
    }
}
