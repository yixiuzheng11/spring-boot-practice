package org.yixz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yixz.common.util.RedisUtil;
import org.yixz.vo.SysMenuVo;


@SpringBootTest
class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        SysMenuVo menuVo = new SysMenuVo();
        redisUtil.set("menuVo", menuVo);
    }

}
