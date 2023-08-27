package org.yixz;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.yixz.dto.MenuDto;
import org.yixz.mapper.MenuMapper;
import org.yixz.service.MenuServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    @Spy
    MenuServiceImpl menuService;

    @Spy
    MenuMapper menuMapper;


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
        ReflectionTestUtils.setField(menuService, "baseMapper", menuMapper);
        menuService.add(new MenuDto());
        System.out.println("test--------");
    }
}
