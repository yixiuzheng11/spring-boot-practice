package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yixz.api.HttpApi;
import org.yixz.common.annotation.IgnoreRestBody;
import org.yixz.entity.dto.req.CmsTaskReq;
import org.yixz.entity.vo.resp.CmsResult;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "httpApi管理")
@RestController
@RequestMapping("/api")
public class HttpApiController {
    @Autowired
    private HttpApi httpApi;

    @Operation(summary = "postToCms")
    @PostMapping("/postToCms")
    public CmsResult postToCms(@RequestBody CmsTaskReq dto) {
        Call<CmsResult> call =  httpApi.postToCms(dto);
        CmsResult cmsResult = null;
        try {
            Response<CmsResult> resp = call.execute();
            cmsResult = resp.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cmsResult;
    }

    @Operation(summary = "eoaAudit")
    @PostMapping("/eoaAudit")
    @IgnoreRestBody
    public CmsResult eoaAudit(@RequestBody CmsTaskReq dto) {
        CmsResult result = new CmsResult();
        result.setCode("aaaa");
        result.setMessage("测试");
        return result;
    }
}
