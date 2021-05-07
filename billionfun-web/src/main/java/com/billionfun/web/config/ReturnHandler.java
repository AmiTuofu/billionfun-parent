package com.billionfun.web.config;

import com.billionfun.common.utils.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuyi
 * @since 2021/5/7 2:58 下午
 */
public class ReturnHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getParameterType() == Result.class;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        if(returnValue instanceof Result) {
            Result result = (Result)returnValue;
            mavContainer.setRequestHandled(true);
            HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
            response.setContentType("application/json;charset=UTF-8;demo=1");
            response.getWriter().append(result.toString()).flush();
        }
    }
}
