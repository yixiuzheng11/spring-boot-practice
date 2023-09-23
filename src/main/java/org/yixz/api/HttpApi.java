package org.yixz.api;


import org.yixz.entity.dto.req.CmsTaskReq;
import org.yixz.entity.vo.resp.CmsResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {

    @POST("/laip-bst-cms-content/whiteList/api/askbob/material/eoaAudit")
    Call<CmsResult> postToCms(@Body CmsTaskReq cmsTaskReq);
}
