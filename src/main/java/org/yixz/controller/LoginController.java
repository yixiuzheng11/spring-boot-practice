package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.yixz.common.util.VerifyImgUtil;
import org.yixz.entity.dto.LoginDto;
import org.yixz.service.LoginService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "登录管理")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private VerifyImgUtil verifyImgUtil;

    @Operation(summary = "登录")
    @PostMapping("/doLogin")
    public String doLogin(@RequestBody LoginDto dto, HttpServletRequest request) {
        return loginService.doLogin(dto, request);
    }

    @Operation(summary = "生产二维码")
    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            verifyImgUtil.geneVerifyCode(request, response, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
