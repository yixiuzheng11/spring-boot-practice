package org.yixz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.yixz.entity.dto.TestDto;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @PostMapping("/testDate")
    public TestDto testDate(@RequestBody TestDto dto) {
        return dto;
    }

    @GetMapping("/testString")
    public String testString() {
        return "ssjsj";
    }
}
