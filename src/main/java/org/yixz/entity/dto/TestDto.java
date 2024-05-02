package org.yixz.entity.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TestDto {
    private Date date;

    private LocalDate localDate;

    private LocalDateTime localDateTime;
}
