package org.yixz.entity.dto;

import lombok.Data;

@Data
public class RedisDto {
    private String key;

    private Object obj;
}
