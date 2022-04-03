package org.yixz;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yixz.service.RedisOptService;
import org.yixz.vo.MenuVo;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
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
