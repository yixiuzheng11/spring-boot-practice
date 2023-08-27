package org.yixz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yixz.service.RedisOptService;
import org.yixz.vo.MenuVo;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootTest
class RedisTest {
    @Autowired
    private RedisOptService redisOptService;

    @Test
    public void test() {
        MenuVo menuVo = new MenuVo();
        menuVo.setCreatedDate(new Date());
        menuVo.setId(1);
        List<MenuVo> list = Arrays.asList(menuVo);
        redisOptService.set("testList", list);
    }

}
