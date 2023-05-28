package org.yixz;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.yixz.dto.MenuDto;
import org.yixz.mapper.MenuMapper;
import org.yixz.service.MenuServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Spy
    MenuServiceImpl menuService;

    @Spy
    MenuMapper menuMapper;

    @Before
    public void before() {
        //Mockito注解初始化
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(menuService, "baseMapper", menuMapper);
    }

    @Test
    public void test() {
        menuService.add(new MenuDto());
    }
}
