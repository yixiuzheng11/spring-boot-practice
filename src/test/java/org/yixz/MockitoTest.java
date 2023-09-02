package org.yixz;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.yixz.dto.SysMenuDto;
import org.yixz.mapper.SysMenuMapper;
import org.yixz.service.SysMenuService;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    @Spy
    SysMenuService menuService;

    @Spy
    SysMenuMapper sysMenuMapper;


    /**
     * 在所有测试方法之前执行的方法
     */
    @BeforeAll
    static void doBefore() {
        System.out.println("开始测试");
    }

    /**
     * 在所有测试方法之后执行的方法
     */
    @AfterAll
    static void doAfter() {
        System.out.println("结束测试");
    }


    @Test
    public void test() {
        ReflectionTestUtils.setField(menuService, "baseMapper", sysMenuMapper);
        menuService.add(new SysMenuDto());
        System.out.println("test--------");
    }
}
